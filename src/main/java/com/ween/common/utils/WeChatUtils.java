package com.ween.common.utils;
import com.thoughtworks.xstream.XStream;
import com.ween.modules.sys.entity.wechat.SimpleMsg;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/7/20.
 */
public class WeChatUtils {
    public static Map<String,String> xmlToMap(HttpServletRequest request){
        Map<String,String> map=new HashMap<String, String>();
        SAXReader reader=new SAXReader();
        InputStream is=null;
        try {
            is=request.getInputStream();
            Document doc=reader.read(is);
            Element root=doc.getRootElement();
            List<Element> list=root.elements();
            for(Element e:list){
                map.put(e.getName(),e.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    public static String mapToXml(SimpleMsg simpleMsg){
        XStream xStream=new XStream();
        xStream.alias("xml",simpleMsg.getClass());
        return xStream.toXML(simpleMsg);
    }
}
