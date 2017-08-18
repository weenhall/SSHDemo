package com.ween.service;

import com.ween.dao.FileDao;
import com.ween.entity.AttFile;
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
}
