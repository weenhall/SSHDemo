package com.ween.common.endecrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wen on 2018/1/5.
 */
public class seaUtil {

    //type:AES,DES,DESede
    public static Map<String, Object> jdkEncode(String type, String str) {
        try {
            Map<String, Object> map = new HashMap<>();
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //KEY转换
            Key key = new SecretKeySpec(keyBytes, type);
            //加密
            Cipher cipher = Cipher.getInstance(type + "/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(str.getBytes());
            map.put("key", key);
            map.put("ciphertext", Base64.encodeBase64String(result));
            map.put("type", type);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String jdkDecode(Map<String, Object> map) {
        try {
            //解密
            Cipher cipher = Cipher.getInstance(map.get("type") + "/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, (Key) map.get("key"));
            byte[] result = cipher.doFinal(Base64.decodeBase64((String) map.get("ciphertext")));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> jdkPBEEncode(String str) {
        try {
            Map<String, Object> map = new HashMap<>();
            //初始化盐
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(8);

            //口令与密钥
            PBEKeySpec pbeKeySpec = new PBEKeySpec(str.toCharArray());

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] result = cipher.doFinal(str.getBytes());
            map.put("key", key);
            map.put("spec",pbeParameterSpec);
            map.put("ciphertext", Base64.encodeBase64String(result));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String jdkPBEDecode(Map<String,Object> map){
        try{
            Cipher cipher=Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.DECRYPT_MODE, (Key) map.get("key"), (AlgorithmParameterSpec) map.get("spec"));
            byte[] relust=cipher.doFinal(Base64.decodeBase64((String) map.get("ciphertext")));
            return new String(relust);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String text = "test word";
//        Map<String, Object> map = jdkEncode("DESede", text);
//        System.out.println(map.get("type")+"加密:" + map.get("ciphertext"));
//        System.out.println(map.get("type")+"解密:" + jdkDecode(map));

        Map<String,Object> map=jdkPBEEncode(text);
        System.out.println("PBE加密:" + map.get("ciphertext"));
        System.out.println("PBE解密:" + jdkPBEDecode(map));
    }
}
