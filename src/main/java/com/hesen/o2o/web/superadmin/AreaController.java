package com.hesen.o2o.web.superadmin;

import com.hesen.o2o.entity.Area;
import com.hesen.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/superadmin")
@Controller
public class AreaController {
    @Autowired
    private AreaService areaService;


    @ResponseBody
    @RequestMapping(value = "/areas", method = RequestMethod.GET)
    public Map<String, Object> getAreaList() {
        Long startTime = System.currentTimeMillis();
        //这里是不是可以使用Spring自带的ModelMap，或者Modal，为啥要自己写？？
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> areaList = null;

        try {
            //为啥要放到try catch中，根本就没啥异常啊？？
            areaList = areaService.getAreaList();
            modelMap.put("success", true);
            modelMap.put("rows", areaList);
            modelMap.put("total", areaList.size());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        return modelMap;
    }

}
