package com.hesen.o2o.service;

import com.hesen.o2o.BaseTest;
import com.hesen.o2o.util.ImageUtil;
import com.hesen.o2o.util.PathUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList() {
        String relativeAddr = PathUtil.getShopImgPath(1L);
        ImageUtil.makeDirs(relativeAddr);
    }
}
