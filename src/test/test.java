
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;

import java.math.BigDecimal;
import java.util.*;
import java.util.Date;
public class test {
    private static Logger logger = LogManager.getLogger("test");

    public static void main(String[] args)  {
//        Pattern pattern = Pattern.compile("^[0-9a-zA-Z_]{1,}$");
//        Matcher matcher = pattern.matcher("ccode_name");
//        for(int i=0;i<50;i++){
//            System.out.println(matcher.matches());
//        }
        System.out.println("你好");
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

    public static void xmlToJson(String msg){
        String messnage="";
        JSONObject xmlJSONobj= XML.toJSONObject(msg);
        org.json.JSONArray jsonArray= (org.json.JSONArray) xmlJSONobj.getJSONObject("ufinterface").get("item");
        String json=xmlJSONobj.toString(4);
        System.out.println(jsonArray.toString());
    }
}