package com.dbdou.arts.easy.url.dao;

import com.dbdou.arts.easy.url.entity.EasyUrl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dentalulcer
 */
@Mapper
public interface UrlDao {

    @Insert("insert into url_${key} values(#{id}, #{code}, #{url})")
    int insert(EasyUrl easyUrl);

    @Select("select `id`, `code`, `url` from url_${key} where `url`=#{url}")
    EasyUrl selectByUrl(EasyUrl easyUrl);

    @Select("select `id`, `code`, `url` from url_${key} where `code`=#{code}")
    EasyUrl selectByCode(EasyUrl easyUrl);

}
