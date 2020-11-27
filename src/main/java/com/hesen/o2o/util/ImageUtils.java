package com.hesen.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ImageUtils {

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();

    /**
     * 感觉很奇怪的函数，不知道各参数的意义，也不知道返回值的意义
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        String saveFilename = getRandomFilename();
        String extension = getFileExtension(thumbnail);
        makeDirs(targetAddr);
        String relativeDir = targetAddr + saveFilename + extension;
        File dest = new File(PathUtils.getImgBasePath() + relativeDir);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeDir;
    }

    public static void makeDirs(String targetAddr) {
         File realAddr = new File(PathUtils.getImgBasePath() + targetAddr);
         if (!realAddr.exists()) {
             realAddr.mkdirs();
         }
    }

    public static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFilename = cFile.getOriginalFilename();
        return originalFilename.substring(originalFilename.indexOf("."));
    }

    public static String getRandomFilename() {
        int rannum = random.nextInt(90000) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }
}
