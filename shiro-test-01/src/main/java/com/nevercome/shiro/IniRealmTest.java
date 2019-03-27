package com.nevercome.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class IniRealmTest {


    private IniRealm iniRealm = new IniRealm("classpath:user.ini");

    @Test
    public void test() {
//      1 构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

//    5 Realm验证
        defaultSecurityManager.setRealm(iniRealm);

//      2 Subject提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

//     3 securityManager进行认证
        UsernamePasswordToken token = new UsernamePasswordToken("sun", "123");
//     4   Authentication认证

        subject.login(token);

        System.out.println("isAuthenticated: " + subject.isAuthenticated());

        subject.checkRoles("admin", "user");

        subject.checkPermission("user:delete");
    }
}
