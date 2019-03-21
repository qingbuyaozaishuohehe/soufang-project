package com.xu.soufang.web.controller.dto;


/**
 * QiNiuPutRet
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public final class QiNiuPutRet {

    public String key;

    public String hash;

    public String bucket;

    public int width;

    public int height;

    @Override
    public String toString() {
        return "QiNiuPutRet{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
