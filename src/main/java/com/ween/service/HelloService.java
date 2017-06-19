package com.ween.service;

import com.ween.dao.HelloDao;
import com.ween.entity.Users;
import com.ween.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/3/15.
 */
@Service
public class HelloService {
    @Autowired
    private HelloDao helloDao;

    public List<Users> getAllUsers(Pager pager){
        return helloDao.getAllUsers(pager);
    }

    public List<Users> getAllUsers(){
        return helloDao.getAllUsers();
    }

    public Map<String,Object> doLogin(Users users) {
       return helloDao.doLogin(users);
    }


}
