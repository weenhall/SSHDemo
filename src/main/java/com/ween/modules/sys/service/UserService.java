package com.ween.modules.sys.service;

import com.ween.modules.sys.dao.UserDao;
import com.ween.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wen on 2017/12/28.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUsersByUemali(String uemail){
        return userDao.getUsersByUemail(uemail);
    }
}
