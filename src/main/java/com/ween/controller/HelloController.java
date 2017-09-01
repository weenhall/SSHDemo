package com.ween.controller;

import com.alibaba.fastjson.JSON;
import com.ween.common.response.Response;
import com.ween.common.response.StoreResponse;
import com.ween.entity.AutoField;
import com.ween.entity.Users;
import com.ween.entity.wechat.SimpleMsg;
import com.ween.service.HelloService;
import com.ween.util.Pager;
import com.ween.util.WeChatUtils;
import com.ween.util.common.PagingInfo;
import com.ween.util.poi.ExcelReadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wen on 2017/3/15.
 */
@Controller
@RequestMapping("/learn")
public class HelloController {
    @Autowired
    private HelloService helloService;

    ReentrantLock lock = new ReentrantLock();

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public String getAllUsers(Model model, Pager pager,String content) {
        List<Users> list = helloService.getAllUsers(pager,content);
        model.addAttribute("msg", list);
        return "bootstrap/hello";
    }

    @RequestMapping(value = "/getUsersByName",method = RequestMethod.GET)
    @ResponseBody
    public String getUsersByName(String content){
        Response response=new Response();
        List<Users> list = helloService.getUsersByName(content);
        response.setSuccess(true);
        response.addAttribute("data",list);
        return response.toString();
    }

    @RequestMapping(value = "/showHandSonTable", method = RequestMethod.GET)
    public String showHandSonTable(Model model) {
        Pager pager = new Pager();
        pager.setCurrentPage(1);
        pager.setPageSize(10);
        List<Users> list = helloService.getAllUsers(pager,"");
        model.addAttribute("msg", list);
        return "handsontable/AutoFieldTable";
    }

    @RequestMapping(value = "/easyuiLayout", method = RequestMethod.GET)
    public String showEasyUI(Model model) {
        return "easyui/easyui";
    }

    @RequestMapping(value = "/easyui_learn", method = RequestMethod.GET)
    public String easyuiLearn(Model model) {
        return "easyui/easyui_learn";
    }

    @RequestMapping(value = "/extJsLayout", method = RequestMethod.GET)
    public String extJsLayout(Model model) {
        return "extjs/extJsLayout";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String fileUpload(Model model) {
        return "jquery-fileupload";
    }

    @RequestMapping(value = "/extjs", method = RequestMethod.GET)
    public String extjsView(Model model) {
        return "extjs/extJsView";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "/statics/jquery/fileupload/login.jsp";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(Users users) {
        Map<String, Object> map = helloService.doLogin(users);
        return "";
    }

    @RequestMapping("/testMultiThread")
    public void testMultiThread(@RequestParam String user) {
        //使用lock
//        if (lock.isLocked()) {
//            System.out.println("当前已有进程");
//            return;
//        }
//        lock.lock();
//        try {
//            for (int i = 0; i < 5; i++) {
//                Thread.sleep(1000);
//                System.out.println(user);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
        //使用synchronized
        try {
            helloService.testMultiThread(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    * easyui分页示例
    * */
    @RequestMapping("/dataGridList")
    @ResponseBody
    public String dataGridList(PagingInfo pagingInfo) {
        StoreResponse storeResponse = new StoreResponse();
        Map<String, Object> map = helloService.getAllUsers(pagingInfo);
        storeResponse.setSuccess(true);
        storeResponse.setRows((List) map.get("list"));
        storeResponse.setTotal((Long) map.get("count"));
        return storeResponse.toString();
    }

    @RequestMapping(value = "/importDemo", method = RequestMethod.POST)
    public void importDemo(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        Response result = new Response();
        try {
            ExcelReadUtil excelReadUtil = new ExcelReadUtil();
            List<Map<String, Object>> list = excelReadUtil.ExcelContentRead(file);
            result.addAttribute("list",list);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(JSON.toJSONString(result));
        }

    }

    @RequestMapping(value = "/wechatTest",method = RequestMethod.GET)
    @ResponseBody
    public void wechatTest(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");
        PrintWriter out=response.getWriter();
        if(helloService.checkSignature(signature,timestamp,nonce)){
            out.print(echostr);
        }
    }

    @RequestMapping(value = "/wechatTest",method = RequestMethod.POST)
    @ResponseBody
    public void wechatSimpleMsg(HttpServletRequest request,HttpServletResponse response){
        try {
            //request.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter out=response.getWriter();
            Map<String,String> map= WeChatUtils.xmlToMap(request);
            String toUserName=map.get("ToUserName");
            String fromUserName=map.get("FromUserName");
            String msgType=map.get("MsgType");
            String content=map.get("Content");

            String message=null;
            if("text".equals(msgType)){
                SimpleMsg simpleMsg=new SimpleMsg();
                simpleMsg.setFromUserName(fromUserName);
                simpleMsg.setToUserName(toUserName);
                simpleMsg.setMsgType("text");
                simpleMsg.setCreateTime(new Date().getTime());
                simpleMsg.setContent("你输入的是:"+content);
                message=WeChatUtils.mapToXml(simpleMsg);
                System.out.println(message);
            }
            out.print(message);
            //out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getAutoFields", method = RequestMethod.GET)
    @ResponseBody
    public String getAutoFields() {
        Response response=new Response();
        List<AutoField> list = helloService.getAutoFields();
        response.setSuccess(true);
        response.addAttribute("list",list);
        return response.toString();
    }
}
