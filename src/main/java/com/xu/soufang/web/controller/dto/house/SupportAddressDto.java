package com.xu.soufang.web.controller.dto.house;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 地址数据传输类
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Data
public class SupportAddressDto {

    private Integer id;

    @JsonProperty(value = "belong_to")
    private String belongTo;

    @JsonProperty(value = "en_name")
    private String enName;

    @JsonProperty(value = "cn_name")
    private String cnName;

    private String level;
}
