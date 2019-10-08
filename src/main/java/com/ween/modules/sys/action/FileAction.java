package com.ween.modules.sys.action;

import com.alibaba.fastjson.JSON;
import com.ween.common.response.Response;
import com.ween.common.response.StoreResponse;
import com.ween.modules.sys.entity.AttFile;
import com.ween.modules.sys.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by wen on 2017/8/3.
 */
@Controller
@Scope("prototype")
@RequestMapping("/file")
public class FileAction {
    @Autowired
    private FileService fileService;

    private Response result;

    @RequestMapping("/fileList")
    @ResponseBody
    public String fileList(@RequestParam String rootId, HttpServletResponse response){
        StoreResponse storeResponse = new StoreResponse();
        List<AttFile> list = fileService.AttFileList(rootId);
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

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Response FileUpload(@RequestParam MultipartFile[] files, HttpServletRequest request, HttpServletResponse response){
        result=new Response();
        String rootId=request.getParameter("rootId");
        List<AttFile> list=new ArrayList<AttFile>();
        long totalSize=0;
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                AttFile attFile = new AttFile();
                attFile.setMainId(rootId);
                attFile.setFileName(fileName);
                attFile.setFileSize(file.getSize());
                attFile.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
                attFile.setContent(file.getBytes());
                attFile.setCreateTime(new Date());
                attFile.setCreator("111");
                list.add(attFile);
                totalSize+=file.getSize();
            }
            if(totalSize>1024*1024*10){
                result.setSuccess(false);
                result.setMessage("文件大小超过10M");
                result.setTitle("提示");
            }else {
                fileService.addFiles(list);
                result.setSuccess(true);
                result.setMessage("上传成功");
                result.setTitle("提示");
            }
        }catch (IOException e) {
            result.setSuccess(false);
            result.setMessage("未知错误"+e.getMessage());
            result.setTitle("系统错误");
            e.printStackTrace();
        }finally {
            files=null;
        }
        response.setContentType("text/html;charset=UTF-8");
        return result;

    }

    @RequestMapping(value = "deleteFileById",method = RequestMethod.POST)
    @ResponseBody
    public String deleteFileById(@RequestParam String id){
        Response response=new Response();
        try{
            fileService.deleteFileById(id);
            response.setSuccess(true);
            response.setMessage("删除成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("未知错误"+e.getMessage());
            result.setTitle("系统错误");
            e.printStackTrace();
        }
        return response.toString();
    }

}
