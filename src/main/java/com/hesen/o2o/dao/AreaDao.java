package com.hesen.o2o.dao;

import com.hesen.o2o.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 查询所有的Area
     * @return areaList
     */
    List<Area> queryArea();
}
