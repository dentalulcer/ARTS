package com.dbdou.arts.easy.url.core;

import cn.hutool.json.JSONUtil;
import com.dbdou.arts.easy.url.dao.KeySeqDao;
import com.dbdou.arts.easy.url.entity.KeySeq;
import com.dbdou.arts.easy.url.entity.SeqModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 序列生成器
 *
 * Created by dentalulcer
 */
@Slf4j
@Component("seqGenerator")
public class SeqGenerator {

    @Resource
    private KeySeqDao keySeqDao;

    /**
     * 内存 map 保存序列信息
     */
    private Map<String, SeqModel> seqMap = new ConcurrentHashMap<>();

    /**
     * 根据 key 生成最新的序列
     * @param key
     * @return
     */
    public long generate(String key) {
        SeqModel seqModel = seqMap.get(key);
        if (seqModel == null) {
            seqModel = new SeqModel(key);
            seqMap.putIfAbsent(key, seqModel);
        }
        // 判断内存序列信息是否增长到阈值
        if (seqModel.getCurrentVal().getAndIncrement() >= seqModel.getThresholdVal()) {
            seqModel = updateSeqVal(key);
        }
        return seqModel.getCurrentVal().get();
    }

    /**
     * 更新 db 中序列当前值，并刷新内存 map 中的序列信息
     * @param key
     * @return
     */
    private synchronized SeqModel updateSeqVal(String key) {
        SeqModel seqModel = seqMap.get(key);
        // 获得锁后再次判断是否需要刷新序列，避免重复刷新，也避免锁范围过大
        if (seqModel.getCurrentVal().getAndIncrement() >= seqModel.getThresholdVal()) {
            log.info("update seq val: current seqModel={}", JSONUtil.toJsonStr(seqModel));
            try {
                // 更新数据库序列：当前值 + 增长因子
                keySeqDao.update(seqModel.getKey());
                // 查询最新的序列信息
                KeySeq keySeq = keySeqDao.selectByKey(seqModel.getKey());
                // 初始化内存中的序列
                seqModel.setCurrentVal(new AtomicLong(keySeq.getCurrentVal()));
                seqModel.setIncrFactor(keySeq.getIncrFactor());
                // 定义阀值
                seqModel.setThresholdVal(keySeq.getCurrentVal() + keySeq.getIncrFactor() - 1);
            } catch (Exception e) {
                log.error("seq update exception：{}", e.getMessage());
            }
        }
        return seqModel;
    }

}
