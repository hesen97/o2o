package com.hesen.o2o.util;

import java.util.regex.Matcher;

public class PathUtil {
    //获取系统的文件分隔符
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name").toLowerCase();
        String basePath = "";
        if (os.startsWith("win")) {
            basePath = "F:/projectDev/image/";
        } else {
            basePath = "/home/hesen/image/";
        }
        return basePath.replaceAll("/", Matcher.quoteReplacement(separator));
    }

    public static String getShopImgPath(long shopId) {
        String shopImgPath = "upload/item/shop/" + shopId + "/";
        return shopImgPath.replaceAll("/", Matcher.quoteReplacement(separator));
    }
}
