package com.hesen.o2o.service;

import com.hesen.o2o.BaseTest;
import com.hesen.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryServiceTest extends BaseTest {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testShopCategoryService() {
        List<ShopCategory> list = shopCategoryService.getShopCategoryList(new ShopCategory());
        assertEquals(list.size(), 1);
    }


}
