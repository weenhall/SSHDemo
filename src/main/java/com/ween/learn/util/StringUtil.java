package com.ween.learn.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by wen on 2017/7/19.
 */
public class StringUtil {

    public static boolean isNumeric(String input) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(input)) {
            return false;
        } else if(input.startsWith("0")){
            return false;
        }else if (input.startsWith("-")) {//是否为负数
            sb.append(input.substring(1));
        } else {
            sb.append(input);
        }
        int index = sb.indexOf(".");
        if (index > 0) {//是否为小数
            return StringUtils.isNumeric(sb.substring(0, index)) && StringUtils.isNumeric(sb.substring(index + 1));
        } else {
            return StringUtils.isNumeric(sb.toString());
        }
    }
}
