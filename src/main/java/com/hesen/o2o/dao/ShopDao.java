package com.hesen.o2o.dao;

import com.hesen.o2o.entity.Shop;

public interface ShopDao {

    int insertShop(Shop shop);

    int updateShop(Shop shop);

    Shop selectShopByShopId(long ShopId);
}
