package com.ween.learn;

import net.glxn.qrgen.image.ImageType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by wen on 2017/5/17.
 */
public class QRCode {
    private static Logger logger = LogManager.getLogger("QRCode");

    public static void main(String[] args) {
        ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from("Hello Wrold").to(ImageType.PNG).stream();
        try {
            String path=System.getProperty("user.dir");
            FileOutputStream fout = new FileOutputStream(new File(path+"/src/main/webapp/statics/images/qr.png"));
            fout.write(out.toByteArray());
            fout.flush();
            fout.close();
            System.out.println("生成完毕");
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}
