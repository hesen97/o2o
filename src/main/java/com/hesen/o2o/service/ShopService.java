package com.hesen.o2o.service;

import com.hesen.o2o.dto.ShopExecution;
import com.hesen.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

public interface ShopService {

    ShopExecution addShop(Shop shop, InputStream shopImageInputStream, String fileName);

    Shop queryShopByShopId(long shopId);

    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName);

    ShopExecution getShopList(Shop condition, int pageIndex, int pageSize);
}
