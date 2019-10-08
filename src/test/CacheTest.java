import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by wen on 2018/11/9
 */
public class CacheTest {
    private static final String CACHE_TEST="test";
    private static CacheManager cacheManager;
    private static Cache cache;
    static {
        cacheManager=CacheManager.create("./src/main/resources/cache/ehcache.xml");
        cache=cacheManager.getCache("sysCache");
    }
    public static void main(String[] args) {
        CacheTest test=new CacheTest();
        MyThread thread=test.new MyThread();
        MyThread thread1=test.new MyThread();
        MyThread thread2=test.new MyThread();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            String str= cache.get(CACHE_TEST)==null?null:(String)cache.get(CACHE_TEST).getObjectValue();
            if(str==null){
                System.out.println("1");
                Element element=new Element(CACHE_TEST,"我是缓存");
                cache.put(element);
            }
            System.out.println(str);
        }
    }
}
