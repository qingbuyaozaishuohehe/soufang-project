package com.xu.soufang.web.controller.dto;

import lombok.Data;

/**
 * QiNiuPutRet
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Data
public class QiNiuPutRet {

    public String key;

    public String hash;

    public String bucket;

    public int width;

    private int height;

}
