package com.xu.soufang.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 瓦力.
 */
@Entity
@Table(name = "house_tag")
@Data
public class HouseTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "house_id")
    private Integer houseId;

    private String name;

    public HouseTag() {
    }

    public HouseTag(Integer houseId, String name) {
        this.houseId = houseId;
        this.name = name;
    }


}
