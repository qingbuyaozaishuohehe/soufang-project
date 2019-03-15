package com.xu.soufang.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ApiResponse
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Controller
public class UserController {

    @GetMapping("/user/login")
    public String loginPage(){
        return "/user/login";
    }

    @GetMapping("/user/center")
    public String centerPage(){
        return "/user/center";
    }

}
