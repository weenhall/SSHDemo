package com.ween.modules.sys.service;

import com.ween.modules.sys.dao.FileDao;
import com.ween.modules.sys.entity.AttFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wen on 2017/8/3.
 */
@Service
public class FileService {
    @Autowired
    private FileDao fileDao;

    public List<AttFile> AttFileList(String mainId){
        return fileDao.AttFileList(mainId);
    }

    public void addFiles(List<AttFile> list) {
        for(AttFile attFile:list){
            fileDao.getSessionFactory().getCurrentSession().saveOrUpdate(attFile);
        }
    }

    public void deleteFileById(String id){
       fileDao.getSession().delete(fileDao.getSession().get(AttFile.class,id));
    }
}
