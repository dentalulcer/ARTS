package com.dbdou.arts.easy.url.core;

import cn.hutool.json.JSONUtil;
import com.dbdou.arts.easy.url.dao.UrlDao;
import com.dbdou.arts.easy.url.entity.EasyUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 异步处理器
 * <p>
 * 简单实现生产者-消费者模式
 * <p>
 * Created by dentalulcer
 */
@Slf4j
@Component
public class AsyncProcessor implements InitializingBean, DisposableBean {

    private BlockingQueue<EasyUrl> blockingQueue;

    private ExecutorService executorService;

    @Autowired
    private UrlDao urlDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.blockingQueue = new LinkedBlockingDeque<>();
        this.executorService = Executors.newFixedThreadPool(10000);

        init();
    }

    private void init() {
        executorService.submit(() -> {
            while (true) {
                EasyUrl easyUrl = blockingQueue.take();
                log.info(blockingQueue.size() + "_" + JSONUtil.toJsonStr(easyUrl));
                if (urlDao.insert(easyUrl) <= 0) {
                    log.error("insert easy url error: easyUrl={}", JSONUtil.toJsonStr(easyUrl));
                }
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        executorService.shutdown();

    }

    public void execute(EasyUrl easyUrl) throws InterruptedException {
        blockingQueue.put(easyUrl);
    }

}
