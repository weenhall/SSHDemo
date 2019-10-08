package com.ween.modules.sys.action;

import com.ween.modules.sys.entity.User;
import com.ween.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wen on 2017/12/28.
 */
@Controller
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "/bootstrap/hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(User users, Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword().toCharArray());
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            model.addAttribute("errorMsg","用户名或密码错误");
            return "login";
//            return new Response(false,"登录失败","用户名或密码错误");
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("errorMsg","用户名或密码错误");
            return "login";//            return new Response(false,"登录失败","用户名或密码错误");
        } catch (AuthenticationException ae) {
            model.addAttribute("errorMsg","未知错误");
            return "login";
//            return new Response(false,"登录失败","未知错误");
        }
        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/user/login";
    }
}
