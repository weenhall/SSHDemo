import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wen on 2017/3/17.
 */
public class WebSpider {
    public static void main(String[] args) {
        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern p = Pattern.compile(regex);
        try {
            url = new URL("http://www.google.cn/");
            urlConnection = url.openConnection();
            pw = new PrintWriter(new FileWriter("d:/url.txt"), true);
            br = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String buf = null;
            while ((buf = br.readLine()) != null) {
                Matcher buf_m = p.matcher(buf);
                while (buf_m.find()) {
                    pw.println(buf_m.group());
                }
            }
            System.out.println("获取成功！");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }
}
