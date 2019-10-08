package com.ween.common.query;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/3/14.
 */
@Repository
@SuppressWarnings("unchecked")
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

    public <T> T load(Class<T> clazz, Serializable id){
        return (T)sessionFactory.getCurrentSession().load(clazz,id);
    }

    public <T> T get(Class<T> clazz,Serializable id){
        return (T)sessionFactory.getCurrentSession().get(clazz,id);
    }

    public void saveOrUpdate(Object object){
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    public <T>List<T> findAll(Class<T> clazz){
        return (List<T>) sessionFactory.getCurrentSession().createCriteria(clazz).list();
    }

    public void  delete(Object object){
        sessionFactory.getCurrentSession().delete(object);
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
