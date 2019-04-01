package com.xu.soufang.service.house;


import com.xu.soufang.service.ServiceMultiResult;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.web.controller.dto.house.HouseDTO;
import com.xu.soufang.web.controller.form.DataTableSearch;
import com.xu.soufang.web.controller.form.HouseForm;

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


    ServiceMultiResult<HouseDTO> adminQuery(DataTableSearch search);
}
