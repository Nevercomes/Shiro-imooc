package com.nevercome.shiroweb.web;

import com.nevercome.shiroweb.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping(value = "/subLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(User user) {
//        主体提交请求进行验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        String res = "";
        if (subject.hasRole("admin")) {
            res = "admin";
        } else {
            res = "no admin";
        }
        if (subject.isPermitted("admin:update")) {
            res += " admin:update";
        } else {
            res += " no admin:update";
        }
        if (subject.isPermitted("admin:add")) {
            res += " admin:add";
        } else {
            res += " no admin:add";
        }
        return res;
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/testRole", method = RequestMethod.GET)
    @ResponseBody
    public String testRole() {
        return "testRole success";
    }

    @RequiresRoles("admin1")
    @RequestMapping(value = "/testRole1", method = RequestMethod.GET)
    @ResponseBody
    public String testRole1() {
        return "testRole1 success";
    }

    @RequiresPermissions("admin:update")
    @RequestMapping(value = "/testAdmin", method = RequestMethod.GET)
    @ResponseBody
    public String testAdmin() {
        return "admin:update success";
    }

    @RequiresPermissions("admin:add")
    @RequestMapping(value = "/testAdmin1", method = RequestMethod.GET)
    @ResponseBody
    public String testAdmin1() {
        return "admin:add success";
    }

}
