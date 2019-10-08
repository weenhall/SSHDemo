package com.ween.common.endecrypt;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by wen on 2018/1/5.
 */
public class Base64Util {

    //jdk方式
    public static String jdkEncrpt(String str){
        BASE64Encoder encoder=new BASE64Encoder();
        return encoder.encode(str.getBytes());
    }

    public static String jdkDecrypt(String str) throws IOException {
        BASE64Decoder decoder=new BASE64Decoder();
        return new String(decoder.decodeBuffer(str));
    }

    //commons方式
    public static String commonsEncode(String str){
       return new String(Base64.encodeBase64(str.getBytes()));
    }

    public static String commonsDecode(String str){
        return new String(Base64.decodeBase64(str));
    }

    //bouncyCastle方式
    public static String bouncyCastleEncode(String str){
        return new String(org.bouncycastle.util.encoders.Base64.encode(str.getBytes()));
    }

    public static String bouncyCastleDecode(String str){
        return new String(Base64.decodeBase64(str));
    }

    public static void main(String[] args) throws IOException {
        String text="test word";
        System.out.println("jdk加密:"+jdkEncrpt(text));
        System.out.println("jdk解密:"+jdkDecrypt(jdkEncrpt(text)));

        System.out.println("commons加密:"+commonsEncode(text));
        System.out.println("commons解密:"+commonsDecode(commonsEncode(text)));

        System.out.println("bouncyCastle加密:"+bouncyCastleEncode(text));
        System.out.println("bouncyCastle解密:"+bouncyCastleDecode(bouncyCastleEncode(text)));
    }
}
