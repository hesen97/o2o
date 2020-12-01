package com.hesen.o2o.web.shopadmin;

import com.hesen.o2o.entity.Area;
import com.hesen.o2o.entity.ShopCategory;
import com.hesen.o2o.service.AreaService;
import com.hesen.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManageController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopInitInfo() {
        Map<String, Object> modalMap = new HashMap<>();
        try {
            List<Area> areaList = areaService.getAreaList();
            List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            modalMap.put("areaList", areaList);
            modalMap.put("shopCategoryList", shopCategoryList);
            modalMap.put("success", true);
        } catch (Exception e) {
            modalMap.put("success", false);
            modalMap.put("errorMsg", e.getMessage());
        }
        return modalMap;
   }

   public Map<String, Object> registShop() {

   }



    private void inputStreamToFile(InputStream in, File file) {
        OutputStream os = null;

        try {
            os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int readByte = 0;
            while ((readByte = in.read(buffer)) != -1) {
                os.write(buffer, 0, readByte);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("关闭io流产生异常：" + e.getMessage());
            }
        }
    }
}
