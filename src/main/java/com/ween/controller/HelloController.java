package com.ween.controller;

import com.ween.common.response.StoreResponse;
import com.ween.entity.Users;
import com.ween.service.HelloService;
import com.ween.util.Pager;
import com.ween.util.common.PagingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public String getAllUsers(Model model, Pager pager) {
        List<Users> list = helloService.getAllUsers(pager);
        model.addAttribute("msg", list);
        return "bootstrap/hello";
    }

    @RequestMapping(value = "/showHandSonTable", method = RequestMethod.GET)
    public String showHandSonTable(Model model) {
        Pager pager = new Pager();
        pager.setCurrentPage(1);
        pager.setPageSize(10);
        List<Users> list = helloService.getAllUsers(pager);
        model.addAttribute("msg", list);
        return "handsontable/handsontable";
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


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "/login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(Users users) {
        Map<String, Object> map = helloService.doLogin(users);
        return "";
    }

    @RequestMapping("/testMultiThread")
    public void testMultiThread(@RequestParam String user) {
        //使用lock或者synchronized
        if (lock.isLocked()) {
            System.out.println("当前已有进程");
            return;
        }
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(user);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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
}
