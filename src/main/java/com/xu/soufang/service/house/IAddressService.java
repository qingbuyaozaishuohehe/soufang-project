package com.xu.soufang.service.house;

import com.xu.soufang.service.ServiceMultiResult;
import com.xu.soufang.web.controller.dto.house.SupportAddressDto;


/**
 * 地址接口实现类
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface IAddressService {

    ServiceMultiResult<SupportAddressDto> findAllCities();

}
