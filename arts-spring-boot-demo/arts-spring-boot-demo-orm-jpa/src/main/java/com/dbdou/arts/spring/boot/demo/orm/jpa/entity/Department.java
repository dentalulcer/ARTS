package com.dbdou.arts.spring.boot.demo.orm.jpa.entity;

import com.dbdou.arts.spring.boot.demo.orm.jpa.entity.base.AbstractAuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by dentalulcer
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orm_department")
@ToString(callSuper = true)
public class Department extends AbstractAuditModel {

    /**
     * 部门名
     */
    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    /**
     * 上级部门id
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "superior", referencedColumnName = "id")
    private Department superior;
    /**
     * 所属层级
     */
    @Column(name = "levels", columnDefinition = "int not null default 0")
    private Integer levels;
    /**
     * 排序
     */
    @Column(name = "order_no", columnDefinition = "int not null default 0")
    private Integer orderNo;
    /**
     * 子部门集合
     */
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "superior")
    private Collection<Department> children;

    /**
     * 部门下用户集合
     */
    @ManyToMany(mappedBy = "departmentList")
    private Collection<User> userList;

}
