package com.ween.controller;

import com.alibaba.fastjson.JSON;
import com.ween.common.response.StoreResponse;
import com.ween.entity.AttFile;
import com.ween.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/8/3.
 */
@Controller
@RequestMapping("/file")
public class FileAction {
    @Autowired
    private FileService fileService;

    @RequestMapping("fileList")
    @ResponseBody
    public String fileList(@RequestParam String mainId, HttpServletResponse response){
        StoreResponse storeResponse = new StoreResponse();
        List<AttFile> list = fileService.AttFileList(mainId);
        storeResponse.setSuccess(true);
        storeResponse.setRows(list);
        storeResponse.setTotal(list.size());
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(JSON.toJSONString(storeResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
