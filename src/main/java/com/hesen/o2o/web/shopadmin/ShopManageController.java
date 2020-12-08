package com.hesen.o2o.web.shopadmin;

import com.hesen.o2o.dto.ShopExecution;
import com.hesen.o2o.entity.Area;
import com.hesen.o2o.entity.PersonInfo;
import com.hesen.o2o.entity.Shop;
import com.hesen.o2o.entity.ShopCategory;
import com.hesen.o2o.enums.ShopStateEnum;
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
import java.util.ArrayList;
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
    public Map<String, Object> registerShop(Shop shop,
                                           @RequestParam("shopImage") CommonsMultipartFile shopImage,
                                           HttpServletRequest request) {
        Map<String, Object> modalMap = new HashMap<>();
//        if (!CaptchaUtil.checkCaptcha(request)) {
//            modalMap.put("success", false);
//            modalMap.put("errorMsg", "验证码输入有误");
//            return modalMap;
//        }

        if (shop != null && shopImage != null) {
//            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
//            shop.setOwner(owner);
            try{
                shopService.addShop(shop, shopImage.getInputStream(), shopImage.getOriginalFilename());
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if (shopList == null) {
                    shopList = new ArrayList<>();
                }
                shopList.add(shop);
                request.getSession().setAttribute("shopList", shopList);
            } catch (Exception e) {
                modalMap.put("success", false);
                modalMap.put("errorMsg", "添加商店失败");
                e.printStackTrace();
            }
        } else {
            modalMap.put("success", false);
            modalMap.put("errorMsg", "请输入商铺信息");
            return modalMap;
        }

        modalMap.put("success", true);
        return modalMap;
    }

    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopById(@RequestParam("shopId") long shopId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Shop shop = shopService.queryShopByShopId(shopId);
            List<Area> areaList = areaService.getAreaList();
            map.put("shop", shop);
            map.put("areaList", areaList);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errorMsg", e.getMessage());
        }
        return map;
    }


    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyShop(Shop shop,
                                      @RequestParam("shopImage") CommonsMultipartFile shopImage) {
        Map<String, Object> map = new HashMap<>();
        if (shop != null && shop.getShopId() != null) {
            try {
                ShopExecution se = null;
                if (shopImage == null) {
                    se = shopService.modifyShop(shop, null, null);
                } else {
                    se = shopService.modifyShop(shop, shopImage.getInputStream(), shopImage.getOriginalFilename());
                }

                if (se.getStateCode() == ShopStateEnum.SUCCESS.getStateCode()) {
                    map.put("success", true);
                } else {
                    map.put("success", false);
                    map.put("errorMsg", se.getStateInfo());
                }
            } catch (Exception e) {
                map.put("success", false);
                map.put("errorMsg", e.getMessage());
            }
        } else {
            map.put("success", false);
            map.put("errorMsg", "商铺信息不全");
        }
        return map;
    }

    @RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopManagementInfo(Long shopId, HttpServletRequest request) {
        Map<String, Object> modalMap = new HashMap<>();
        if (shopId == null) {
            Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
            if (currentShop == null) {
                modalMap.put("redirect", true);
                modalMap.put("url", "/o2o/shopadmin/shoplist");
            } else {
                modalMap.put("redirect", false);
                modalMap.put("shopId", currentShop.getShopId());
            }
        } else {
            modalMap.put("redirect", false);
            modalMap.put("shopId", shopId);
        }
        return modalMap;
    }

    //获取当前登陆用户所属的商铺
    @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modalMap = new HashMap<>();

        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("hesen");
        request.getSession().setAttribute("owner", user);

        PersonInfo owner = (PersonInfo) request.getSession().getAttribute("owner");
        Shop condition = new Shop();
        condition.setOwner(owner);

        try {
            //0 , 100的含义实际上是获取该用户的所有商铺
            ShopExecution se = shopService.getShopList(condition, 0, 100);
            if (se.getStateCode() == ShopStateEnum.SUCCESS.getStateCode()) {
                modalMap.put("shopList", se.getShopList());
                modalMap.put("count", se.getCount());
                modalMap.put("success", true);
            } else {
                modalMap.put("errorMsg", se.getStateInfo());
                modalMap.put("success", false);
            }
        } catch (Exception e) {
            modalMap.put("errorMsg", e.getMessage());
            modalMap.put("success", false);
        }
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
