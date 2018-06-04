package com.ween.modules.oauth;

import cn.hutool.http.HttpConnection;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ween.common.config.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.annotation.XmlElementDecl;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class OauthController {
    private String AccessTokenUrl="https://github.com/login/oauth/access_token?";
    private String UserInfoUrl="";

    @RequestMapping("/oauthCallback")
    public void oauthCallback(String code){
        String clientId="client_id="+Global.getConfig("github.clientId");
        String secretId="&client_secret="+Global.getConfig("github.clientSecret");
        code="&code="+code;
        String redirectURL="&redirect_uri="+Global.getConfig("github.redirectURL");
        String params=sendGet(AccessTokenUrl+clientId+secretId+code+redirectURL);

        String accessToken=params.split("&")[0];
        String res=sendGet("https://api.github.com/user?"+accessToken+"");
        JSONObject user= (JSONObject) JSON.parse(res);
        System.out.println(user);
    }


    public static String sendGet(String req_url){
        StringBuffer buffer=new StringBuffer();
        try {
            URL url=new URL(req_url);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            String str=null;
            while ((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();

            inputStream.close();
            inputStream=null;
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
