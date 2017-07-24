import com.ween.entity.Users;
import com.ween.entity.wechat.SimpleMsg;
import com.ween.learn.poi.Person;
import com.ween.util.common.MathUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.StringMatchFilter;
import org.hibernate.annotations.SourceType;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    private static Logger logger = LogManager.getLogger("test");

    public static void main(String[] args) throws ParseException, InterruptedException {
//        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
//        map.put("11",BigDecimal.ZERO);
//        System.out.println("1");
//        for(String str:map.keySet()){
//            System.out.println(str);
//        }
//        System.out.println((short)1);
        Date d=new Date();
        java.sql.Date date=new java.sql.Date(d.getTime());
        System.out.println(date.getTime()+"--"+Long.valueOf("1500622872262"));
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

    public static void testArrayList() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("subjectcode", "�??013599");
        map.put("ccode", "101");
        map.put("zffs", "1");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("subjectcode", "�??013599");
        map.put("ccode", "102");
        map.put("zffs", "1");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("subjectcode", "�??013599");
        map.put("ccode", "101");
        map.put("zffs", "1");
        list.add(map);
        Map<String, String> m1 = null;
        Map<String, String> m2 = null;
        for (int i = 0; i < list.size(); i++) {
            m1 = list.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    m2 = list.get(j);
                    if (m1.get("ccode").equals(m2.get("ccode"))) {
                        list.get(i).put("ccode", m2.get("ccode") + "1101");
                    }
                }
            }
        }
        System.out.println(list);
    }

    public static boolean isNumeric(String input) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(input)) {
            return false;
        } else if (input.startsWith("-")) {//是否为负数??
            sb.append(input.substring(1));
        } else {
            sb.append(input);
        }
        int index = sb.indexOf(".");
        if (index > 0) {//是否为小数??
            return StringUtils.isNumeric(sb.substring(0, index)) && StringUtils.isNumeric(sb.substring(index + 1));
        } else {
            return StringUtils.isNumeric(sb.toString());
        }
    }

}