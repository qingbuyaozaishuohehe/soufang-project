package com.xu.soufang.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 权限javabean
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    private String name;




}
