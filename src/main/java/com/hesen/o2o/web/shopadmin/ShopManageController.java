package com.hesen.o2o.web.shopadmin;

import java.io.*;

public class ShopManageController {

    private void inputStreamToFile(InputStream in, File file) {
        OutputStream os = null;

        try {
            os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int readByte = 0;
            while ((readByte = in.read(buffer)) != -1) {
                os.write(buffer, 0, readByte);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("关闭io流产生异常：" + e.getMessage());
            }
        }
    }
}
