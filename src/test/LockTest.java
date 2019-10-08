import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wen on 2017/6/1.
 */
public class LockTest {
    public static void main(String[] args) {
        sellTicket st = new sellTicket();
        Thread t1 = new Thread(st, "窗口1");
        Thread t2 = new Thread(st, "窗口2");
        Thread t3 = new Thread(st, "窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

}

class sellTicket implements Runnable {
    private int ticket = 10;
    private Lock lock = new ReentrantLock();

    public void run() {
        for (int i=0;i<10;i++) {
            lock.lock();
            if (ticket > 0) {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + (ticket--) + "张票");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
    }
}
}
