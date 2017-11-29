package com.ween.dao;

import com.ween.util.common.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wen on 2017/3/14.
 */
@Repository
public class BasicDao {
    @Autowired
    protected SessionFactory sessionFactory;
    public SessionFactory getSessionFactory(){
        return this.sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }


    public Query initQueryParam(String hql,Map<String,Object> map){
        Query query=getSessionFactory().getCurrentSession().createQuery(hql);
        if(map!=null){
            for(String str:map.keySet()){
                query.setParameter(str,map.get(str));
                System.out.println(str+"-"+map.get(str));
            }
        }
        return query;
    }

}
