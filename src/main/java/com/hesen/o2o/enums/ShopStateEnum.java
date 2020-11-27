package com.hesen.o2o.enums;

public enum ShopStateEnum {

    CHECK(0, "审核中"), OFFLINE(-1, "非法商铺"),
    SUCCESS(1, "操作成功"), PASS(2, "审核通过"),
    INNER_ERROR(-1001, "内部错误"), NULL_SHOPID(-1002, "shopId为空"),
    NULL_SHOP_INFO(-1003, "传入了空的信息");


    //错误码
    private int stateCode;

    //错误解释信息
    private String stateInfo;

    private ShopStateEnum(int stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }

    public int getStateCode() {
        return stateCode;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ShopStateEnum stateOf(int stateCode) {
        for (ShopStateEnum state : values()) {
            if (state.getStateCode() == stateCode) {
                return state;
            }
        }
        return null;
    }
}
