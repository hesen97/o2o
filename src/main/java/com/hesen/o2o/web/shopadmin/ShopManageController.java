package com.hesen.o2o.web.shopadmin;

import com.hesen.o2o.entity.Area;
import com.hesen.o2o.entity.PersonInfo;
import com.hesen.o2o.entity.Shop;
import com.hesen.o2o.entity.ShopCategory;
import com.hesen.o2o.service.AreaService;
import com.hesen.o2o.service.ShopCategoryService;
import com.hesen.o2o.service.ShopService;
import com.hesen.o2o.util.CaptchaUtil;
import com.hesen.o2o.util.ImageUtil;
import com.hesen.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    private ShopService shopService;
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

   @RequestMapping(value = "/registershop", method = RequestMethod.POST)
   @ResponseBody
   public Map<String, Object> registerShop(@RequestBody String shop,
//                                           @RequestParam("shopImg")CommonsMultipartFile shopImg,
                                           HttpServletRequest request) {
        Map<String, Object> modalMap = new HashMap<>();
        if (!CaptchaUtil.checkCaptcha(request)) {
            modalMap.put("success", false);
            modalMap.put("errorMsg", "验证码输入有误");
            return modalMap;
        }

//        if (shop != null && shopImg != null) {
//            PersonInfo owner = new PersonInfo();
//            owner.setUserId(1L);
//            shop.setOwner(owner);
//            try{
//                shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
//            } catch (Exception e) {
//                modalMap.put("success", false);
//                modalMap.put("errorMsg", "添加商店失败");
//                e.printStackTrace();
//            }
//        } else {
//            modalMap.put("success", false);
//            modalMap.put("errorMsg", "请输入商铺信息");
//            return modalMap;
//        }

        modalMap.put("success", true);
        return modalMap;
   }


//    //将输入流转换为File类
//    private void inputStreamToFile(InputStream in, File file) {
//        OutputStream os = null;
//
//        try {
//            os = new FileOutputStream(file);
//            byte[] buffer = new byte[1024];
//            int readByte = 0;
//            while ((readByte = in.read(buffer)) != -1) {
//                os.write(buffer, 0, readByte);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//
//                if (os != null) {
//                    os.close();
//                }
//            } catch (Exception e) {
//                throw new RuntimeException("关闭io流产生异常：" + e.getMessage());
//            }
//        }
//    }
}
