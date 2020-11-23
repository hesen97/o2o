package com.hesen.o2o.entity;

import java.util.Date;

public class WeChatAuth {
    private Long weChatAuthId;
    //暂时不知道这是干嘛的？
    private String openId;
    private Date createTime;
    //注意外键存放的是与外键关联的对象
    private PersonInfo personInfo;

    public Long getWeChatAuthId() {
        return weChatAuthId;
    }

    public void setWeChatAuthId(Long weChatAuthId) {
        this.weChatAuthId = weChatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
