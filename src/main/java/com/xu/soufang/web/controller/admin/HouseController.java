package com.xu.soufang.web.controller.admin;

import com.sun.tracing.dtrace.ModuleAttributes;
import com.xu.soufang.base.ApiResponse;
import com.xu.soufang.base.RentValueBlock;
import com.xu.soufang.service.ServiceMultiResult;
import com.xu.soufang.service.ServiceResult;
import com.xu.soufang.service.house.IAddressService;
import com.xu.soufang.service.house.IHouseService;
import com.xu.soufang.web.controller.dto.house.HouseDTO;
import com.xu.soufang.web.controller.dto.house.SubwayDTO;
import com.xu.soufang.web.controller.dto.house.SubwayStationDTO;
import com.xu.soufang.web.controller.dto.house.SupportAddressDto;
import com.xu.soufang.web.controller.form.RentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 房屋controller
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Controller
public class HouseController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IHouseService houseService;

    @GetMapping("address/support/cities")
    @ResponseBody
    public ApiResponse getSupportAddress(){
        ServiceMultiResult<SupportAddressDto> allCities = addressService.findAllCities();
        if (allCities.getResultSize() == 0){
            return ApiResponse.ofSuccess(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(allCities);
    }



    /**
     * 获取对应城市支持区域列表
     * @param cityEnName
     * @return
     */
    @GetMapping("address/support/regions")
    @ResponseBody
    public ApiResponse getSupportRegions(@RequestParam(name = "city_name") String cityEnName) {
        ServiceMultiResult<SupportAddressDto> addressResult = addressService.findAllRegionsByCityName(cityEnName);
        if (addressResult.getResult() == null || addressResult.getTotal() < 1) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(addressResult.getResult());
    }

    /**
     * 获取具体城市所支持的地铁线路
     * @param cityEnName
     * @return
     */
    @GetMapping("address/support/subway/line")
    @ResponseBody
    public ApiResponse getSupportSubwayLine(@RequestParam(name = "city_name") String cityEnName) {
        List<SubwayDTO> subways = addressService.findAllSubwayByCity(cityEnName);
        if (subways.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }

        return ApiResponse.ofSuccess(subways);
    }


    /**
     * 获取对应地铁线路所支持的地铁站点
     * @param subwayId
     * @return
     */
    @GetMapping("address/support/subway/station")
    @ResponseBody
    public ApiResponse getSupportSubwayStation(@RequestParam(name = "subway_id") Integer subwayId) {
        List<SubwayStationDTO> stationDTOS = addressService.findAllStationBySubway(subwayId);
        if (stationDTOS.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(stationDTOS);
    }


    @GetMapping("/rent/house")
    public String rentHouse(@ModelAttribute RentSearch rentSearch,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes){
        if (rentSearch.getCityEnName() == null){
            String cityEnNameInCity = (String) session.getAttribute("cityEnName");
            if (cityEnNameInCity == null){
                redirectAttributes.addAttribute("msg","must choose city");
                return "redirect:/index";
            } else {
              rentSearch.setCityEnName(cityEnNameInCity);
            }
        } else {
            session.setAttribute("cityEnName",rentSearch.getCityEnName());
        }

        ServiceResult<SupportAddressDto> city = addressService.findCity(rentSearch.getCityEnName());
        if (!city.isSuccess()){
            redirectAttributes.addAttribute("msg","must choose city");
            return "redirect:/index";
        }

        model.addAttribute("currentCity",city.getResult());

        ServiceMultiResult<SupportAddressDto> allRegionsByCityName = addressService.findAllRegionsByCityName(rentSearch.getCityEnName());
        if (allRegionsByCityName.getResult() == null || allRegionsByCityName.getTotal() < 1){
            redirectAttributes.addAttribute("mag","must choose city");
        }

        ServiceMultiResult<HouseDTO> serviceMultiResult= houseService.query(rentSearch);

        model.addAttribute("total", serviceMultiResult.getTotal());
        model.addAttribute("houses",serviceMultiResult.getResult());

        if (rentSearch.getRegionEnName() == null){
            rentSearch.setRegionEnName("*");
        }

        model.addAttribute("searchBody",rentSearch);
        model.addAttribute("regions",allRegionsByCityName.getResult());

        model.addAttribute("areaBlocks", RentValueBlock.AREA_BLOCK);
        model.addAttribute("priceBlocks", RentValueBlock.PRICE_BLOCK);

        model.addAttribute("currentPriceBlocks",RentValueBlock.matchPrice(rentSearch.getPriceBlock()));
        model.addAttribute("currentAreaBlocks",RentValueBlock.matchArea(rentSearch.getAreaBlock()));

        return "rent-list";
    }

}
