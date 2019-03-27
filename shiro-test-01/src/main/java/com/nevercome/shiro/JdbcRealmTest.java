package com.nevercome.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {


    private DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("");
    }


    private JdbcRealm jdbcRealm = new JdbcRealm();


    @Test
    public void test() {
//      1 构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

//    5 Realm验证
        jdbcRealm.setDataSource(dataSource);
        defaultSecurityManager.setRealm(jdbcRealm);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String userSql = "select password from test_user where username = ?";
        jdbcRealm.setAuthenticationQuery(userSql);

        String roleSql = "select role_name from test_user_roles where username = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        String permSql = "select permission from test_roles_permissions where role_name = ?";
        jdbcRealm.setPermissionsQuery(permSql);

//      2 Subject提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

//     3 securityManager进行认证
        UsernamePasswordToken token = new UsernamePasswordToken("sun", "123");
//     4   Authentication认证

        subject.login(token);

        System.out.println("isAuthenticated: " + subject.isAuthenticated());

        subject.checkRoles("admin", "user");

        subject.checkPermissions("user:add", "admin:update");
    }

}
