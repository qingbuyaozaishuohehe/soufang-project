package com.xu.soufang.repository;

import com.xu.soufang.entity.SupportAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 地址接口
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public interface SupportAddressRepository extends CrudRepository<SupportAddress,Integer> {

    /**
     * 获取所有对应行政级别的信息
     * @return
     */
    public List<SupportAddress> findAllByLevel(String a);

}
