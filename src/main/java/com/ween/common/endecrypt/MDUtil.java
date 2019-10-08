package com.ween.common.endecrypt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.groovy.runtime.powerassert.SourceText;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wen on 2018/1/5.
 */
public class MDUtil {

    public static String MD5Encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes=md.digest(str.getBytes());
            return Hex.encodeHexString(md5Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String MD5Decrypt(String str){
        int len=str.toCharArray().length;
        char []data=str.toCharArray();
        byte[] out=new byte[len>>1];
        for(int i=0,j=0;j<len;i++){
            int f=toDigit(data[j],j)<<4;
            f=f|toDigit(data[j],j);
            j++;
            out[i]= (byte) (f&0xFF);
        }
        return new String(out);
    }

    protected static int toDigit(char ch,int index){
        return Character.digit(ch,32);
    }


    public static void main(String[] args) throws DecoderException {
        String text="test word";
        String mdStr="74a9fd0f75b68420c19aea784277a20a";
//        System.out.println(MD5Decrypt(MD5Decrypt(mdStr)));
        String input="123456";
        byte[] code = null;
        try {
            code = MessageDigest.getInstance("md5").digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            code = input.getBytes();
        }
        BigInteger bi = new BigInteger(code);
        System.out.println(bi.abs().toString(32).toUpperCase());
    }
}
