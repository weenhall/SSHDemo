package com.ween.dao;

import com.ween.entity.Users;
import com.ween.util.Pager;
import com.ween.util.common.PagingInfo;
import org.hibernate.Query;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/3/15.
 */
@Repository
public class HelloDao extends BasicDao {
    public List<Users> getAllUsers(Pager pager) {
        String hql = "from Users user";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setFirstResult(pager.getCurrentPage());
        query.setMaxResults(pager.getPageSize());
        List<Users> list = query.list();
        return list;
    }

    public Map<String,Object> getAllUsers(PagingInfo pagingInfo) {
        Map<String,Object> resutlMap=new HashMap<String, Object>();
        String hql = "from Users user";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        long count=query.list().size();
        query.setFirstResult((pagingInfo.getPage()-1)*pagingInfo.getRows());
        query.setMaxResults(pagingInfo.getRows());
        List<Users> list = query.list();
        resutlMap.put("list",list);
        resutlMap.put("count",count);
        return resutlMap;
    }

    public Map<String,Object> doLogin(final Users users) {
        final String sql="select sum(age) from users where username=?";
        return getSessionFactory().getCurrentSession().doReturningWork(new ReturningWork<Map<String, Object>>() {
            public Map<String, Object> execute(Connection connection) throws SQLException {
                PreparedStatement ps=connection.prepareStatement(sql);
                ps.setString(1,users.getUsername());
                ResultSet rs=ps.executeQuery();
                Map<String,Object> map=new HashMap<String, Object>();
                if(rs.next()){
                    map.put("a",rs.getDouble(1));
                }
                return map;
            }
        });
    }
}
