package com.ween.dao;

import com.ween.entity.Users;
import com.ween.util.Pager;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wen on 2017/3/15.
 */
@Repository
public class HelloDao extends BasicDao {
    public List<Users> getAllUsers(Pager pager) {
        String hql = "from Users user";
        Query query = sessionFactory.openSession().createQuery(hql);
        query.setFirstResult(pager.getCurrentPage());
        query.setMaxResults(pager.getPageSize());
        List<Users> list = query.list();
        return list;
    }
}
