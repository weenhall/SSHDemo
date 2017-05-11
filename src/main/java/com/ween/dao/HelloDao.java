package com.ween.dao;

import com.ween.entity.Users;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wen on 2017/3/15.
 */
@Repository
public class HelloDao extends BasicDao {
    public List<Users> getAllUsers() {
        String hql = "from Users user";
        Query query = sessionFactory.openSession().createQuery(hql);
        List<Users> list = query.list();
        return list;
    }
}
