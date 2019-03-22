package com.xu.soufang.repository;

import com.xu.soufang.entity.Subway;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 地铁接口
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface SubWayRepository extends CrudRepository<Subway,Integer> {


    /**
     * 根据城市名查询地铁
     * @param cityEnName
     * @return
     */
    List<Subway> findAllByCityEnName(String cityEnName);


}
