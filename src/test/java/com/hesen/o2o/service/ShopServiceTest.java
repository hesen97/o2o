package com.hesen.o2o.service;

import com.hesen.o2o.BaseTest;
import com.hesen.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws Exception {
        Shop shop = new Shop();
        shop.setShopName("测试service层File改为InputStream");

        File shopImg = new File("F:\\images\\shop1.jpg");
        InputStream shopImgInputStrream = new FileInputStream(shopImg);
        shopService.addShop(shop, shopImgInputStrream, shopImg.getName());
    }
}
