package com.xu.soufang.service.house;

import com.xu.soufang.entity.SupportAddress;
import com.xu.soufang.service.ServiceMultiResult;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.web.controller.dto.house.SubwayDTO;
import com.xu.soufang.web.controller.dto.house.SubwayStationDTO;
import com.xu.soufang.web.controller.dto.house.SupportAddressDto;

import java.util.List;
import java.util.Map;


/**
 * 地址接口实现类
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface IAddressService {


    /**
     * 查询所有城市
     * @return
     */
    ServiceMultiResult<SupportAddressDto> findAllCities();

    /**
     * 根据城市名查询该城市所有区域
     * @param cityName
     * @return
     */
    ServiceMultiResult<SupportAddressDto> findAllRegionsByCityName(String cityName);


    /**
     * 根据城市名 查询所有的地铁
     * @param cityName
     * @return
     */
    List<SubwayDTO> findAllSubwayByCity(String cityName);


    /**
     * 根据地铁id查询地铁信息
     * @param subwayId
     * @return
     */
    List<SubwayStationDTO> findAllStationBySubway(Integer subwayId);

    /**
     * 根据英文简写获取具体区域的信息
     * @param cityEnName
     * @param regionEnName
     * @return
     */
    Map<SupportAddress.Level, SupportAddressDto> findCityAndRegion(String cityEnName, String regionEnName);

    /**
     * 根据城市英文简写获取城市详细信息
     * @param cityEnName
     * @return
     */
    ServiceResult<SupportAddressDto> findCity(String cityEnName);

}
