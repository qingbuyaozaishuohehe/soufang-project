package com.xu.soufang.web.controller.form;

import lombok.Data;

import java.util.Date;

/**
 * @author xuzhenqin
 *
 */
@Data
public class DataTableSearch {

    private int draw;


    private int start;

    private int length;

    private Integer status;

    private Date createTimeMin;

    private Data createTimeMax;




}
