import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.annotations.SourceType;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    private static Logger logger = LogManager.getLogger("test");

    public static void main(String[] args) throws ParseException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-05-01 09:24:57");
        Date d2 = sdf.parse("2017-05-04 14:51:29");
        String str = "";
//        List<String> list = new ArrayList<String>();
//        list.add("小明");
//        list.add("小黄");
//        list.add("小率");
//        list.add("小红");
//        for (int i = 0; i < list.size(); i++) {
//            if (i > 2) {
//                str = str.substring(0, str.length() - 1) + "等";
//                break;
//            }
//            str = str + list.get(i) + ",";
//        }
//        str=str.endsWith(",")?str.substring(0,str.length()-1):str;
//        System.out.println(str);
        Set<String> set=new HashSet<String>();
        set.add("ZT001");
        set.add("ZT001");
        for(String st1:set){
            System.out.println(st1);
        }
    }

    public static void testSwitch(int input) {
        BigDecimal bd = BigDecimal.ZERO;
        switch (input) {
            case 600:
            case 800:
                System.out.println(bd.add(new BigDecimal("800")));
                break;
            case 3360:
                System.out.println(bd.add(new BigDecimal("3360")));
                break;
            case 21000:
                System.out.println(bd.add(new BigDecimal("21000")));
                break;
            case 49500:
                System.out.println(bd.add(new BigDecimal("49500")));
                break;
            default:
                System.out.println("0");
        }
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date paserDate(String strDate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(strDate);
    }

    public static boolean ArraysContain(String[] arr, String str) {
        return ArrayUtils.contains(arr, str);
    }

    public static void ListTest() {
        List list = new ArrayList();
        list.add("aa");
        list.add(0, "bb");
        list.add("cc");
        list.add("dd");
        System.out.println(list.toString());
    }

    public static void testTimeComplex() {
        String a = "aa";
        Date aa = new Date();
        if ("aa".equals(a)) {
            a = "bb";
            System.out.println(new Date().getTime() - aa.getTime());
        }
        Date bb = new Date();
        a = "bb".equals(a) ? "aa" : "";
        System.out.println(new Date().getTime() - bb.getTime());
    }
}