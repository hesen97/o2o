package com.hesen.o2o.dao;

import com.hesen.o2o.BasicTest;
import com.hesen.o2o.entity.Area;
import com.hesen.o2o.entity.PersonInfo;
import com.hesen.o2o.entity.Shop;
import com.hesen.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BasicTest {
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
        assertEquals(1, effectNum);
    }
}
