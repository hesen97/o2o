package com.hesen.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ImageUtil {

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();

    /**
     * 感觉很奇怪的函数，不知道各参数的意义，也不知道返回值的意义
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(InputStream thumbnail, String fileName, String targetAddr) {
        String realFilename = getRandomFilename();
        String extension = getFileExtension(fileName);
        makeDirs(targetAddr);
        String relativeDir = targetAddr + realFilename + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeDir);
        try {
            Thumbnails.of(thumbnail).size(200, 200)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeDir;
    }

    public static void makeDirs(String targetAddr) {
         File realAddr = new File(PathUtil.getImgBasePath() + targetAddr);
         if (!realAddr.exists()) {
             realAddr.mkdirs();
         }
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.indexOf("."));
    }

    public static String getRandomFilename() {
        int rannum = random.nextInt(90000) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }
}
