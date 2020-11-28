package com.hesen.o2o.service.impl;

import com.hesen.o2o.dao.ShopDao;
import com.hesen.o2o.dto.ShopExecution;
import com.hesen.o2o.entity.Shop;
import com.hesen.o2o.enums.ShopStateEnum;
import com.hesen.o2o.service.ShopService;
import com.hesen.o2o.util.ImageUtil;
import com.hesen.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    /**
     * 这个方法需要之后好好再看，结合前端
     * @param shop
     * @param shopImg
     * @return
     */
    @Override
    @Transactional()
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        //判断非空
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }

        try {
            //添加商铺信息，这些信息都是前端无法改变的，也无法提供。
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(shop.getCreateTime());
            int effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0) {
                throw new RuntimeException("新增商铺失败");
            } else {
                //存储图片
                if (shopImg != null) {
                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new RuntimeException("add ShopImg error: " + e.getMessage());
                    }
                }

                //调用addShopImg的时候，图片信息已经被存储到传入参数shop中
                //更新数据库中shop的信息
                effectNum = shopDao.updateShop(shop);
                if (effectNum <= 0) {
                    throw new RuntimeException("更新图片地址失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }


    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        String relativeAddr = PathUtil.getShopImgPath(shop.getShopId());
        //generateThumbnail最终返回的是商店图片的相对目录加上图片名字
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, relativeAddr);
        //添加图片的同时还要修改商店的图片信息
        shop.setShopImg(shopImgAddr);
    }
}
