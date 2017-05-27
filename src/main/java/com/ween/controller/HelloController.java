package com.ween.controller;

import com.ween.entity.Users;
import com.ween.service.HelloService;
import com.ween.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by wen on 2017/3/15.
 */
@Controller
@RequestMapping("/home")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/getAllUsers" ,method = RequestMethod.GET)
    public String getAllUsers(Model model, Pager pager){
        List<Users> list=helloService.getAllUsers(pager);
        model.addAttribute("msg",list);
        return "/hello";
    }

    @RequestMapping(value = "/showHandSonTable",method = RequestMethod.GET)
    public String showHandSonTable(Model model){
        Pager pager=new Pager();
        pager.setCurrentPage(1);
        pager.setPageSize(10);
        List<Users> list=helloService.getAllUsers(pager);
        model.addAttribute("msg",list);
        return "/handsontable";
    }
    @RequestMapping(value = "/showEasyUI",method = RequestMethod.GET)
    public String showEasyUI(Model model){
        return "/easyui";
    }


    @RequestMapping(value = "/login",method =RequestMethod.GET)
    public String login(Model model){
        return "/login";
    }

    @RequestMapping(value = "/dologin",method =RequestMethod.POST)
    public String doLogin(Users users){
        return null;
    }
}
