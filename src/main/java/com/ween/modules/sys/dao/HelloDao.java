package com.ween.modules.sys.dao;

import com.ween.common.query.BasicDao;
import com.ween.modules.sys.entity.AutoField;
import com.ween.modules.sys.entity.User;
import com.ween.common.utils.Pager;
import com.ween.common.utils.PagingInfo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.jdbc.ReturningWork;
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
    public List<User> getAllUsers(Pager pager, String content) {
        StringBuilder hql=new StringBuilder("from User user ");
        Map<String,Object> paramMap=new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(content)){
            hql.append("where user.username like :a");
            paramMap.put("a",content+"%");
        }
        hql.append(" order by user.id");
        Query query=initQueryParam(hql.toString(),paramMap);
        query.setFirstResult((pager.getCurrentPage()-1)*pager.getPageSize());
        query.setMaxResults(pager.getPageSize());
        List<User> list = query.list();
        return list;
    }

    public Map<String,Object> getAllUsers(PagingInfo pagingInfo) {
        Map<String,Object> resutlMap=new HashMap<String, Object>();
        String hql = "from User user";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        long count=query.list().size();
        query.setFirstResult((pagingInfo.getPage()-1)*pagingInfo.getRows());
        query.setMaxResults(pagingInfo.getRows());
        List<User> list = query.list();
        resutlMap.put("list",list);
        resutlMap.put("count",count);
        return resutlMap;
    }

    public Map<String,Object> doLogin(final User users) {
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

    public List<AutoField> getAutoFields() {
        String hql = "from AutoField autoField";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        List<AutoField> list = query.list();
        return list;
    }
}
