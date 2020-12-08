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

    public ShopExecution(ShopStateEnum shopStateEnum) {
        this.stateCode = shopStateEnum.getStateCode();
        this.stateInfo = shopStateEnum.getStateInfo();
    }

    public ShopExecution(ShopStateEnum shopStateEnum, Shop shop) {
        this(shopStateEnum);
        this.shop = shop;
    }

    public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> shopList) {
        this(shopStateEnum);
        this.shopList = shopList;
    }

    public ShopExecution() {}

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @Override
    public String toString() {
        return "ShopExecution{" +
                "stateCode=" + stateCode +
                ", stateInfo='" + stateInfo + '\'' +
                ", count=" + count +
                ", shop=" + shop +
                ", shopList=" + shopList +
                '}';
    }
}
