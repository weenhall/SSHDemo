package com.ween.util;

import com.ween.dao.BasicDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/3/14.
 */
@Controller
@RequestMapping("/ween")
public class HelloSpringMVC {

    @Autowired
    private BasicDao basicDao;

    @RequestMapping(value = "/hello")
    public String SayHello(Model model, @RequestParam String msg) {
        Session session = basicDao.getSessionFactory().openSession();
        Query query = session.createSQLQuery("select * from users");
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        System.out.println(list);
        model.addAttribute("msg",list);
        return "/hello";
    }
}
