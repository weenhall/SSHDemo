package com.ween.modules.sys.utils;

import com.ween.common.utils.SpringContextHolder;
import com.ween.modules.sys.dao.UserDao;
import com.ween.modules.sys.entity.User;

/**
 * Created by wen on 2017/12/28.
 */
public class UserUtils {
    private static UserDao userDao=SpringContextHolder.getBean(UserDao.class);

    public static User getUsersByUemail(String uemail){
        return userDao.getUsersByUemail(uemail);
    }
}
