package com.ween.common.utils;

import java.math.BigDecimal;

/**
 * Created by wen on 2017/7/19.
 */
public class MathUtils {

    public static BigDecimal sum(BigDecimal... decimals){
        BigDecimal bigDecimal=BigDecimal.ZERO;
        for(BigDecimal decimal:decimals){
            if (decimal !=null) {
                bigDecimal=bigDecimal.add(decimal);
            }
        }
        return bigDecimal;
    }

    public static BigDecimal mul(BigDecimal...decimals){
        BigDecimal bigDecimal=BigDecimal.ONE;
        for(BigDecimal decimal:decimals){
            if(decimal!=null){
                bigDecimal=bigDecimal.multiply(decimal);
            }
        }
        return bigDecimal;
    }

    public static BigDecimal sub(BigDecimal minuend,BigDecimal... subtracts){
        for(BigDecimal decimal:subtracts){
            if(decimal!=null){
                minuend=minuend.subtract(decimal);
            }
        }
        return minuend;
    }

    public static BigDecimal div(BigDecimal dividend,BigDecimal... divisors){
        for(BigDecimal decimal:divisors){
            if(decimal!=null){
                dividend=dividend.divide(decimal);
            }
        }
        return dividend;
    }
}
