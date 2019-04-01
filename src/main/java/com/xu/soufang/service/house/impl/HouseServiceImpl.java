package com.xu.soufang.service.house.impl;

import com.xu.soufang.base.HouseStatus;
import com.xu.soufang.base.LoginUserUtil;
import com.xu.soufang.entity.House;
import com.xu.soufang.entity.HouseDetail;
import com.xu.soufang.entity.Subway;
import com.xu.soufang.entity.SubwayStation;
import com.xu.soufang.repository.HouseDetailRespository;
import com.xu.soufang.repository.HouseRepository;
import com.xu.soufang.repository.SubWayRepository;
import com.xu.soufang.repository.SubwayStationRepository;
import com.xu.soufang.service.ServiceMultiResult;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.service.house.IHouseService;
import com.xu.soufang.web.controller.dto.house.HouseDTO;
import com.xu.soufang.web.controller.form.DataTableSearch;
import com.xu.soufang.web.controller.form.HouseForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

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

    @Override
    public ServiceMultiResult<HouseDTO> adminQuery(DataTableSearch search) {
        List<HouseDTO> houseDTOList = new ArrayList<>();

        Sort orders = new Sort(Sort.Direction.fromString(search.getDirection()),search.getOrderBy());

        int page = search.getStart() / search.getLength();

        Pageable pageable = PageRequest.of(page,search.getLength(),orders);

        Specification<House> specification = (root,query,cb) -> {
                Predicate predicate = cb.equal(root.get("adminId"),LoginUserUtil.getLoginUserId());
                predicate = cb.and(predicate,cb.notEqual(root.get("status"), HouseStatus.DELETED.getValue()));

                if (search.getCity() != null) {
                    predicate = cb.and(predicate,cb.equal(root.get("cityEnName"),search.getCity()));
                }

                if (search.getStatus() != null){
                    predicate = cb.and(predicate,cb.equal(root.get("status"),search.getStatus()));
                }

                if (search.getCreateTimeMin() != null){
                    predicate = cb.and(predicate,cb.greaterThanOrEqualTo(root.get("createTime"),search.getCreateTimeMin()));
                }

                if (search.getCreateTimeMax() != null){
                    predicate = cb.and(predicate,cb.lessThanOrEqualTo(root.get("createTime"),search.getCreateTimeMax()));
                }

                if (search.getTitle() != null){
                    predicate = cb.and(predicate,cb.like(root.get("title"), "%" + search.getTitle() + "%"));
                }

                return predicate;
        };


        Page<House> houses = houseRepository.findAll(specification,pageable);

        houses.forEach(house -> {
            HouseDTO houseDTO = modelMapper.map(house,HouseDTO.class);
            houseDTO.setCover(this.cdnPrefix);
            houseDTOList.add(houseDTO);
        });

        return new ServiceMultiResult<>(Integer.valueOf(houses.getTotalElements()+""),houseDTOList);
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
