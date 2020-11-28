package com.hesen.o2o.service;

import com.hesen.o2o.BaseTest;
import com.hesen.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws Exception {
        Shop shop = new Shop();
        shop.setShopName("何森奶茶店");

        File shopImg = new File("F:\\images\\shop1.jpg");
        shopService.addShop(shop, shopImg);
    }
}
