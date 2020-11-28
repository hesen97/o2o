package com.hesen.o2o.util;

public class PathUtil {
    //获取系统的文件分隔符
    private static String seperator = System.getProperty("file.seperator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name").toLowerCase();
        String basePath = "";
        if (os.startsWith("win")) {
            basePath = "F:/projectDev/image/";
        } else {
            basePath = "/home/hesen/image/";
        }
        return basePath.replaceAll("/", seperator);
    }

    public static String getShopImgPath(long shopId) {
        String shopImgPath = "/upload/item/shop/" + shopId + "/";
        return shopImgPath.replaceAll("/", seperator);
    }
}
