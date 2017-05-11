package com.ween.controller;

import com.ween.entity.Users;
import com.ween.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wen on 2017/3/15.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/getAllUsers" ,method = RequestMethod.GET)
    public String getAllUsers(Model model){
        List<Users> list=helloService.getAllUsers();
        model.addAttribute("msg",list);
        return "/hello";
    }

    @RequestMapping(value = "/showHandSonTable",method = RequestMethod.GET)
    public String showHandSonTable(Model model){
        List<Users> list=helloService.getAllUsers();
        model.addAttribute("msg",list);
        return "/handsontable";
    }
}
