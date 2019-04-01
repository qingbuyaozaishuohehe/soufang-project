package com.xu.soufang.repository;

import com.xu.soufang.entity.House;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xuzhenqin 2019/03/29
 * 房屋repository
 *
 */
public interface HouseRepository extends PagingAndSortingRepository<House,Integer>, JpaSpecificationExecutor {
}
