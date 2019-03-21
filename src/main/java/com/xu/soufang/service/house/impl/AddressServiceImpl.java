package com.xu.soufang.service.house.impl;

import com.xu.soufang.entity.SupportAddress;
import com.xu.soufang.repository.SupportAddressRepository;
import com.xu.soufang.service.house.IAddressService;
import com.xu.soufang.web.controller.dto.house.SupportAddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SupportAddressDto> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());

        List<SupportAddressDto> supportAddressDtos = new ArrayList<>();

        for (SupportAddress address : addresses) {
            SupportAddressDto target = modelMapper.map(address,SupportAddressDto.class);
            supportAddressDtos.add(target);
        }
        return supportAddressDtos;
    }
}
