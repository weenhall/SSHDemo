package com.ween.modules.sys.dao;

import com.ween.common.query.BasicDao;
import com.ween.modules.sys.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2017/12/28.
 */
@Repository
public class UserDao extends BasicDao {

    public User getUsersByUemail(String username){
        String hql="from User u where u.username=?";
        User users= (User) getSessionFactory().getCurrentSession().createQuery(hql).setString(0,username).uniqueResult();
        return users;
    }
}
