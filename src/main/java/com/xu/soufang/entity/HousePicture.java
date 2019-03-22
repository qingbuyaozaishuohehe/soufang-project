package com.xu.soufang.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 19/3/22
 * Created by 瓦力.
 */
@Entity
@Table(name = "house_picture")
@Data
public class HousePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "house_id")
    private Integer houseId;

    private String path;

    @Column(name = "cdn_prefix")
    private String cdnPrefix;

}
