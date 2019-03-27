package com.nevercome.shiro;

import com.nevercome.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class CustomRealmTest {

    @Test
    public void test() {
//      1 构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //    5 Realm验证
        CustomRealm customRealm = new CustomRealm();
        defaultSecurityManager.setRealm(customRealm);
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(1);
        matcher.setHashAlgorithmName("md5");
        customRealm.setCredentialsMatcher(matcher);


//      2 Subject提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

//     3 securityManager进行认证
        UsernamePasswordToken token = new UsernamePasswordToken("sun", "123");
//     4   Authentication认证

        subject.login(token);

        System.out.println("isAuthenticated: " + subject.isAuthenticated());

//        subject.checkRoles("admin", "user");
//        subject.checkPermissions("user:add", "admin:add");
    }
}
