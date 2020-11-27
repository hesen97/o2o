package com.hesen.o2o.dto;

import com.hesen.o2o.entity.Shop;
import com.hesen.o2o.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    //结果状态
    private Integer stateCode;
    //结果解释信息
    private String stateInfo;
    //店铺数量
    private Integer count;
    //操作的店铺(增删改店铺的时候使用)
    private Shop shop;
    //操作的店铺(插叙的时候使用)
    private List<Shop> shopList;

    //没有店铺信息，操作失败的时候使用
    public ShopExecution(ShopStateEnum shopStateEnum) {
        this.stateCode = shopStateEnum.getStateCode();
        this.stateInfo = shopStateEnum.getStateInfo();
    }

    //操作成功时使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum, Shop shop) {
        this(shopStateEnum);
        this.shop = shop;
    }

    //操作成功时使用的构造器
    public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> shopList) {
        this(shopStateEnum);
        this.shopList = shopList;
    }
}
