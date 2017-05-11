package com.ween.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2017/3/14.
 */
@Repository
public class BasicDao {
    @Autowired
    protected SessionFactory sessionFactory;
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }

}
