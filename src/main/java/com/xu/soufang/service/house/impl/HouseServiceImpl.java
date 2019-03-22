package com.xu.soufang.service.house.impl;

import com.xu.soufang.base.LoginUserUtil;
import com.xu.soufang.entity.House;
import com.xu.soufang.entity.HouseDetail;
import com.xu.soufang.entity.Subway;
import com.xu.soufang.entity.SubwayStation;
import com.xu.soufang.repository.HouseDetailRespository;
import com.xu.soufang.repository.HouseRepository;
import com.xu.soufang.repository.SubWayRepository;
import com.xu.soufang.repository.SubwayStationRepository;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.service.house.IHouseService;
import com.xu.soufang.web.controller.dto.house.HouseDTO;
import com.xu.soufang.web.controller.form.HouseForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xuzhenqin 19/03/22
 * 房屋实现类
 */
@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubWayRepository subWayRepository;

    @Autowired
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private HouseDetailRespository houseDetailRespository;

    @Override
    public ServiceResult<HouseDTO> save(HouseForm houseForm) {
        House house = new House();
        modelMapper.map(houseForm,House.class);

        Date now = new Date();
        house.setCreateTime(now);
        house.setLastUpdateTime(now);
        house.setAdminId(LoginUserUtil.getLoginUserId());
        houseRepository.save(house);

        HouseDetail houseDetail = new HouseDetail();
        houseDetail.setHouseId(house.getId());


        return null;
    }

    private ServiceResult<HouseDTO> wrapperDetailInfo(HouseDetail houseDetail,HouseForm houseForm){
        Subway subway = subWayRepository.findById(houseForm.getSubwayLineId()).get();
        if (subway == null){
            return new ServiceResult<>(false,"Not valid subway line!");
        }

        SubwayStation subwayStation = subwayStationRepository.findById(houseForm.getSubwayStationId()).get();
        if (subwayStation == null || !subway.getId().equals(subwayStation.getSubwayId())){
            return new ServiceResult<>(false,"Not valid subway line!");
        }
        houseDetail.setSubwayLineId(subway.getId());
        houseDetail.setSubwayLineName(subway.getName());

        houseDetail.setSubwayStationId(subwayStation.getId());
        houseDetail.setSubwayStationName(subwayStation.getName());

        houseDetail.setDescription(houseForm.getDescription());
        houseDetail.setDetailAddress(houseForm.getDetailAddress());
        houseDetail.setLayoutDesc(houseForm.getLayoutDesc());
        houseDetail.setRoundService(houseForm.getRoundService());
        houseDetail.setTraffic(houseForm.getTraffic());
        return null;
    }
}
