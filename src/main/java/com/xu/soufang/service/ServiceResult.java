package com.xu.soufang.service;

import lombok.Data;

/**
 * @author xuzhenqin 19/3/22
 * 服务接口通用结构
 */
@Data
public class ServiceResult<T> {


    private boolean success;

    private String message;

    private T result;


    public ServiceResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }



}
