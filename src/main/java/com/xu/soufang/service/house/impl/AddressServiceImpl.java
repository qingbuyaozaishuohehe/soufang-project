package com.xu.soufang.service.house.impl;

import com.xu.soufang.entity.Subway;
import com.xu.soufang.entity.SubwayStation;
import com.xu.soufang.entity.SupportAddress;
import com.xu.soufang.repository.SubWayRepository;
import com.xu.soufang.repository.SubwayStationRepository;
import com.xu.soufang.repository.SupportAddressRepository;
import com.xu.soufang.service.ServiceMultiResult;
import com.xu.soufang.service.house.IAddressService;
import com.xu.soufang.web.controller.dto.house.SubwayDTO;
import com.xu.soufang.web.controller.dto.house.SubwayStationDTO;
import com.xu.soufang.web.controller.dto.house.SupportAddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private SubWayRepository subWayRepository;

    @Autowired
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceMultiResult<SupportAddressDto> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());

        List<SupportAddressDto> supportAddressDtos = new ArrayList<>();

        for (SupportAddress address : addresses) {
            SupportAddressDto target = modelMapper.map(address,SupportAddressDto.class);
            supportAddressDtos.add(target);
        }
        return new ServiceMultiResult<>(supportAddressDtos.size(),supportAddressDtos);
    }

    @Override
    public ServiceMultiResult<SupportAddressDto> findAllRegionsByCityName(String cityName) {
        List<SupportAddressDto> result = new ArrayList<>();
        List<SupportAddress> regionList= supportAddressRepository.findAllByLevelAndBelongTo(SupportAddress.Level.CITY.getValue(), cityName);
        regionList.forEach(region -> result.add(modelMapper.map(region,SupportAddressDto.class)));
        return new ServiceMultiResult<>(regionList.size(),result);
    }

    @Override
    public List<SubwayDTO> findAllSubwayByCity(String cityName) {
        List<SubwayDTO> subwayDTOS = new ArrayList<>();
        List<Subway> subwayList = subWayRepository.findAllByCityEnName(cityName);
        if (subwayList.isEmpty()){
            return subwayDTOS;
        }
        subwayList.forEach(subway -> subwayDTOS.add(modelMapper.map(subway,SubwayDTO.class)));
        return subwayDTOS;
    }

    @Override
    public List<SubwayStationDTO> findAllStationBySubway(Integer subwayId) {
        List<SubwayStationDTO> result = new ArrayList<>();
        List<SubwayStation> stations = subwayStationRepository.findAllBySubwayId(subwayId);
        if (stations.isEmpty()) {
            return result;
        }
        stations.forEach(station -> result.add(modelMapper.map(station, SubwayStationDTO.class)));
        return result;
    }

    @Override
    public Map<SupportAddress.Level, SupportAddressDto> findCityAndRegion(String cityEnName, String regionEnName) {
        return null;
    }

}
