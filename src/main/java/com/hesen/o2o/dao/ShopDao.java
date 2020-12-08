package com.hesen.o2o.dao;

import com.hesen.o2o.entity.Shop;

import java.util.List;
import java.util.Map;

public interface ShopDao {

    int insertShop(Shop shop);

    int updateShop(Shop shop);

    Shop selectShopByShopId(long ShopId);

    /**
     * criterion中可能包含，店铺名、店铺状态、店铺分类、店铺区域、店铺所有者
     * @param criterion
     * @return
     */
    List<Shop> selectShopWithCriterion(Map<String, Object> criterion);

    int countShopWithCriterion(Map<String, Object> criterion);
}
