package com.xu.soufang.service;

import lombok.Data;

import java.util.List;

/**
 * 通用多结果Service返回结构
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Data
public class ServiceMultiResult<T> {
    private Long total;

    private List<T> result;

    public int getResultSize(){
        if (this.result==null){
            return 0;
        }
        return this.result.size();
    }


}
