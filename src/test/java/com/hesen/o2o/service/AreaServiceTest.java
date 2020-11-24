package com.hesen.o2o.service;

import com.hesen.o2o.BasicTest;
import com.hesen.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BasicTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("东区", areaList.get(0).getAreaName());
    }
}
