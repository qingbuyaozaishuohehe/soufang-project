package com.xu.soufang.web.controller.admin;

import com.xu.soufang.base.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 房屋controller
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Controller
public class HouseController {


    @GetMapping("address/support/cities")
    public ApiResponse getSupportAddress(){
        return ApiResponse.ofSuccess(null);
    }
}
