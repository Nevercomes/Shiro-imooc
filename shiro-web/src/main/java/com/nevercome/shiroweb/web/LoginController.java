package com.nevercome.shiroweb.web;

import com.nevercome.shiroweb.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
        return "success";
    }

}
