package com.dbdou.arts.spring.boot.demo.orm.jpa.repository;

import com.dbdou.arts.spring.boot.demo.orm.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dentalulcer
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
