package com.ween.learn;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import sun.net.www.http.HttpClient;

import java.io.*;

/**
 * Created by wen on 2017/5/17.
 */
public class CaptchaDown {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        DefaultHttpClient httpClient=new DefaultHttpClient();
        for(int i=0;i<10;i++){
            String url="http://xxjw.hnust.cn/xxjw/verifycode.servlet";
            HttpGet getMethod=new HttpGet(url);
            try{
                HttpResponse response = httpClient.execute(getMethod, new BasicHttpContext());
                HttpEntity entity = response.getEntity();
                InputStream instream = entity.getContent();
                OutputStream outstream = new FileOutputStream(new File("d:/pdf", i + ".png"));
                int l = -1;
                byte[] tmp = new byte[2048];
                while ((l = instream.read(tmp)) != -1) {
                    outstream.write(tmp);
                }
                outstream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                getMethod.releaseConnection();
            }
            System.out.println("download success");
        }
    }
}
