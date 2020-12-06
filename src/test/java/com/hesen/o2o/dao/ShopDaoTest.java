package com.hesen.o2o.dao;

import com.hesen.o2o.BaseTest;
import com.hesen.o2o.entity.Area;
import com.hesen.o2o.entity.PersonInfo;
import com.hesen.o2o.entity.Shop;
import com.hesen.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop() {
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        PersonInfo owner = new PersonInfo();
        area.setAreaId(10);
        shopCategory.setShopCategoryId(1L);
        owner.setUserId(1L);

        Shop shop = new Shop();
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setOwner(owner);
        shop.setShopName("天行健");
        shop.setShopDesc("东区食堂");
        shop.setShopAddr("东区");
        shop.setEnableStatus(1);

        int effectNum = shopDao.insertShop(shop);
        System.out.println(shop.getShopId());
    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopName("测试更新");
        shop.setShopDesc("测试更新");
        shop.setLastEditTime(new Date());

        int effectTime = shopDao.updateShop(shop);
        assertEquals(1, effectTime);
    }

    @Test
    public void testSelectShopByShopId() {
        long shopId = 4L;
        Shop shop = shopDao.selectShopByShopId(shopId);
        System.out.println(shop);
    }
}
