package com.xu.soufang.service.house;


import com.xu.soufang.service.ServiceMultiResult;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.web.controller.dto.house.HouseDTO;
import com.xu.soufang.web.controller.form.DataTableSearch;
import com.xu.soufang.web.controller.form.HouseForm;
import com.xu.soufang.web.controller.form.RentSearch;

/**
 * @author xuzhenqin 19/03/22
 * 房屋service接口
 */
public interface IHouseService {


    /**
     * 保存房屋
     * @param houseForm
     * @return
     */
    ServiceResult<HouseDTO> save(HouseForm houseForm);


    /**
     * 根据表单查询房屋
     * @param search
     * @return
     */
    ServiceMultiResult<HouseDTO> adminQuery(DataTableSearch search);

    /**
     * 根据条件查询房屋 前台
     * @param rentSearch
     * @return
     */
    ServiceMultiResult<HouseDTO> query(RentSearch rentSearch);
}
