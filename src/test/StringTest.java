import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by wen on 2017/6/19.
 */
public class StringTest {

    @SuppressWarnings("deprecation")
    @Test
    public void test() throws Exception {
        String m = "hello,world";
        String n = "hello,world";
        String u = new String(m);
        String v = new String("hello,world");

        Field f = m.getClass().getDeclaredField("value");
        f.setAccessible(true);
        char[] cs = (char[]) f.get(m);
        cs[0] = 'H';

        String p = "Hello,world";
        Assert.assertEquals(p, m);
        Assert.assertEquals(p, n);
        Assert.assertEquals(p, u);
        Assert.assertEquals(p, v);
    }

    @Test
    public void test1() {

        int hangshu = 7;
        int yiban = hangshu / 2 + 1;
        int yibanduo = hangshu / 2;
        System.out.println("空心菱形：");
        for (int k = 1; k <= yiban; k++) {
            for (int i = 1; i <= (yiban - k); i++) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int i = 1; i <= ((k - 2) * 2 + 1); i++) {
                System.out.print(" ");
            }
            if (k != 1) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int k = yibanduo; k >= 1; k--) {
            for (int i = 1; i <= (yiban - k); i++) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int i = 1; i <= ((k - 2) * 2 + 1); i++) {
                System.out.print(" ");
            }
            if (k != 1) {
                System.out.print("*");
            }
            System.out.println();
        }


        System.out.println("实心菱形：");
        for (int i = 1; i <= hangshu; i++) {
            if (i <= hangshu / 2 + 1) {
                for (int k = 1; k <= hangshu / 2 + 1 - i; k++) {
                    System.out.print(" ");
                }
                for (int k = 1; k <= i; k++) {
                    System.out.print("* ");
                }
                System.out.println();
            } else {
                for (int k = 1; k <= (i - (hangshu / 2 + 1)); k++) {
                    System.out.print(" ");
                }
                for (int k = 1; k <= (2 * (hangshu / 2 + 1) - i); k++) {
                    System.out.print("* ");
                }
                System.out.println();
            }
        }
    }
}
