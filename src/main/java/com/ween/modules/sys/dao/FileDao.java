package com.ween.modules.sys.dao;

import com.ween.common.query.BasicDao;
import com.ween.modules.sys.entity.AttFile;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wen on 2017/8/3.
 */
@Repository
public class FileDao extends BasicDao {
    public List<AttFile> AttFileList(String mainId) {
        String hql = "from AttFile attFile where mainId=?";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0,mainId);
        return query.list();
    }

}
