package com.dbdou.arts.url.dao;

import com.dbdou.arts.url.entity.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dentalulcer
 */
@Mapper
public interface UrlDao {

    @Insert("insert into url(`code`, `url`, `url_md5`) values(#{code}, #{url}, #{urlMd5})")
    int insert(Url url);

    @Select("select `id`, `code`, `url`, `url_md5` from url where `url_md5`=#{urlMd5}")
    Url selectByUrl(@Param("urlMd5") String urlMd5);

    @Select("select `id`, `code`, `url`, `url_md5` from url where `code`=#{code}")
    Url selectByCode(@Param("code") String code);

}
