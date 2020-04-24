package com.dbdou.arts.easy.url.dao;

import com.dbdou.arts.easy.url.entity.KeySeq;
import org.apache.ibatis.annotations.*;

/**
 * Created by dentalulcer
 */
@Mapper
public interface KeySeqDao {

    @Update("update key_seq set current_val = current_val + incr_factor where `key`=#{key,jdbcType=VARCHAR}")
    int update(String key);

    @Results(id = "keyResult", value = {
        @Result(property = "key", column = "key"),
        @Result(property = "incrFactor", column = "incr_factor"),
        @Result(property = "currentVal", column = "current_val")
    })
    @Select("select `key`, incr_factor, current_val from key_seq where `key`=#{key,jdbcType=VARCHAR}")
    KeySeq selectByKey(String key);

}
