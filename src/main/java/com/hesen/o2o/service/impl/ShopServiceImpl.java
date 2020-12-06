package com.hesen.o2o.service.impl;

import com.hesen.o2o.dao.ShopDao;
import com.hesen.o2o.dto.ShopExecution;
import com.hesen.o2o.entity.Shop;
import com.hesen.o2o.enums.ShopStateEnum;
import com.hesen.o2o.exception.ShopOperationException;
import com.hesen.o2o.service.ShopService;
import com.hesen.o2o.util.ImageUtil;
import com.hesen.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    /**
     * 这个方法要再看看
     * @param shop
     * @param shopImageInputStream
     * @param fileName
     * @return
     */
    @Override
    @Transactional()
    public ShopExecution addShop(Shop shop, InputStream shopImageInputStream, String fileName) {
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
                throw new ShopOperationException("新增商铺失败");
            } else {
                //存储图片
                if (shopImageInputStream != null) {
                    try {
                        addShopImg(shop, shopImageInputStream, fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("add ShopImg error: " + e.getMessage());
                    }
                }

                //调用addShopImg的时候，图片信息已经被存储到传入参数shop中
                //更新数据库中shop的信息
                effectNum = shopDao.updateShop(shop);
                if (effectNum <= 0) {
                    throw new ShopOperationException("更新图片地址失败");
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop queryShopByShopId(long shopId) {
        return shopDao.selectShopByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        } else {
            try {
                //说明需要更换图片
                if (shopImgInputStream != null && fileName != null) {
                    Shop oldShop = shopDao.selectShopByShopId(shop.getShopId());
                    if (oldShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(oldShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                shop.setLastEditTime(new Date());
                shopDao.updateShop(shop);
                return new ShopExecution(ShopStateEnum.SUCCESS, shop);
            } catch (Exception e) {
                throw new ShopOperationException("修改商店失败:" + e.getMessage());
            }
        }
    }


    private void addShopImg(Shop shop, InputStream shopImageInputStream, String fileName) {
        String relativeAddr = PathUtil.getShopImgPath(shop.getShopId());
        //generateThumbnail最终返回的是商店图片的相对目录加上图片名字
        String shopImgAddr = ImageUtil.generateThumbnail(shopImageInputStream, fileName, relativeAddr);
        //添加图片的同时还要修改商店的图片信息
        shop.setShopImg(shopImgAddr);
    }
}
