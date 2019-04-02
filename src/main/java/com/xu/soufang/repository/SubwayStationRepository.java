package com.xu.soufang.repository;

import com.xu.soufang.entity.SubwayStation;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.web.controller.dto.house.SupportAddressDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * SubwayStationRepository
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface SubwayStationRepository extends CrudRepository<SubwayStation,Integer> {

    /**
     *
     * @param subwayId
     * @return
     */
    List<SubwayStation> findAllBySubwayId(Integer subwayId);


}
