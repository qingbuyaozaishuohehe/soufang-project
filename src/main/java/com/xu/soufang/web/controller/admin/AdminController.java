package com.xu.soufang.web.controller.admin;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.xu.soufang.base.ApiDataTableResponse;
import com.xu.soufang.base.ApiResponse;
import com.xu.soufang.entity.SupportAddress;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.service.house.IAddressService;
import com.xu.soufang.service.house.IHouseService;
import com.xu.soufang.service.house.IQiNiuService;
import com.xu.soufang.web.controller.dto.QiNiuPutRet;
import com.xu.soufang.web.controller.dto.house.HouseDTO;
import com.xu.soufang.web.controller.dto.house.SupportAddressDto;
import com.xu.soufang.web.controller.form.HouseForm;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


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
    private Gson gson;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IHouseService houseService;

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

    @GetMapping("/admin/house/list")
    public String adminHouseList(){
        return "admin/house-list";
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
                QiNiuPutRet qiNiuPutRet = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                return ApiResponse.ofSuccess(qiNiuPutRet);
            }else {
                return ApiResponse.ofMessage(response.statusCode,response.getInfo());
            }
        } catch (QiniuException e ){
            Response response = e.response;
            try {
                return ApiResponse.ofMessage(response.statusCode,response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
            }
        }catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
       /* File target = new File("D:/workspace/myproject/soufang/tmp/"+fileName);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
        return ApiResponse.ofSuccess(null);*/
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


    @PostMapping("/admin/add/house")
    @ResponseBody
    public ApiResponse addHouse(@ModelAttribute("form-house-add")HouseForm houseForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(),bindingResult.getAllErrors().get(0).getDefaultMessage(),null);
        }
        if (houseForm.getPhotos() == null || houseForm.getCover() == null){
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),"必须上传图片");
        }
        Map<SupportAddress.Level, SupportAddressDto> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());
        if (addressMap.keySet().size() != 2){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        ServiceResult<HouseDTO> result = houseService.save(houseForm);
        if (result.isSuccess()){
            return ApiResponse.ofSuccess(result.getResult());
        }
        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }

    @PostMapping("admin/houses")
    @ResponseBody
    public ApiDataTableResponse houses(){
        return null;
    }

}
