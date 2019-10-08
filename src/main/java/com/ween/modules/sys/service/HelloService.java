package com.ween.modules.sys.service;

import com.ween.common.utils.DateUtil;
import com.ween.modules.sys.dao.HelloDao;
import com.ween.modules.sys.entity.AutoField;
import com.ween.modules.sys.entity.User;
import com.ween.common.utils.Pager;
import com.ween.common.utils.PagingInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wen on 2017/3/15.
 */
@Service
public class HelloService {
    @Autowired
    private HelloDao helloDao;

    private Lock lock=new ReentrantLock();

    public List<User> getAllUsers(Pager pager, String content) {
        if(pager.getCurrentPage()==0){
            pager.setCurrentPage(1);
            pager.setPageSize(30);
        }
        return helloDao.getAllUsers(pager,content);
    }

    public List<User> getUsersByName(String content) {
        Pager pager=new Pager();
        pager.setCurrentPage(1);
        pager.setPageSize(5);
        return helloDao.getAllUsers(pager,content);
    }

    public Map<String, Object> getAllUsers(PagingInfo pagingInfo) {
        return helloDao.getAllUsers(pagingInfo);
    }

    public Map<String, Object> doLogin(User users) {
        return helloDao.doLogin(users);
    }


    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = "hello";
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String str : arr) {
            content.append(str);
        }
        String temp = getSha1(content.toString());
        return temp.equals(signature);
    }

    public String getSha1(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(input.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte bt = md[i];
                buf[k++] = hexDigits[bt >>> 4 & 0xf];
                buf[k++] = hexDigits[bt & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AutoField> getAutoFields() {
        return helloDao.getAutoFields();
    }

    public synchronized void testMultiThread(String user) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println(user);
        }
    }

    public void testLock(String code) {
        try{
            System.out.println(code);
            if("a".equals(code)){
                lock.lock();
                Thread.sleep(5000);
            }
            System.out.println(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss")+"-"+code);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if("a".equals(code)){
                lock.unlock();
            }
        }
    }
}
