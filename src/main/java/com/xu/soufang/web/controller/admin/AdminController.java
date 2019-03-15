package com.xu.soufang.web.controller.admin;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.xu.soufang.base.ApiResponse;
import com.xu.soufang.service.house.IQiNiuService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * 测试
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Controller
public class AdminController {

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private Gson gsonl;

    @GetMapping("/admin/center")
    public String adminCenterPage(){
        return "admin/center";
    }

    @GetMapping("/admin/welcome")
    public String welcomePage(){
        return "/admin/welcome";
    }

    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }

    @GetMapping("/admin/add/house")
    private String addHousePage(){
        return "admin/house-add";
    }

    @PostMapping(value = "admin/upload/photo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    private ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file){
        if (file==null){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        String fileName = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            Response response = qiNiuService.uploadFile(inputStream);
            if (response.isOK()){
                    
                /*return ApiResponse.ofSuccess()*/
            }else {
                return ApiResponse.ofMessage(response.statusCode,response.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
        File target = new File("D:/workspace/myproject/soufang/tmp/"+fileName);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
        return ApiResponse.ofSuccess(null);
        }


    @GetMapping(value = "admin/qiniu/upload/photo")
    @ResponseBody
    public String testUploadFile(){
        String filePath = "C:\\Users\\WIN10\\Pictures\\Camera Roll\\u=1986179278,1118313821&fm=27&gp=0.jpg";

        File file = new File(filePath);

        Assert.assertTrue(file.exists());

        try {
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
            return "ok";
        } catch (QiniuException e) {
            e.printStackTrace();
            return "wrong";
        }
    }

}
