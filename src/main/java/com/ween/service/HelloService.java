package com.ween.service;

import com.ween.dao.HelloDao;
import com.ween.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wen on 2017/3/15.
 */
@Service
public class HelloService {
    @Autowired
    private HelloDao helloDao;

    public List<Users> getAllUsers(){
        return helloDao.getAllUsers();
    }
}
