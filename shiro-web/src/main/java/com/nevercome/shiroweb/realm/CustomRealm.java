package com.nevercome.shiroweb.realm;

import com.nevercome.shiroweb.dao.UserDao;
import com.nevercome.shiroweb.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;
//    Map<String, String> userMap = new HashMap();
//    Set<String> roles = new HashSet<String>();
//    Set<String> permissions = new HashSet<String>();
//    {
//        userMap.put("sun", "1d7b217127d82ea1eac7e3b92090a463");
//        roles.add("admin");
//        roles.add("user");
//        permissions.add("user:add");
//        permissions.add("admin:add");
//        super.setName("customRealm");
//    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();
//        从数据库或者缓存中获取角色信息
        Set<String> roles = getRolesByUsername(username);
        Set<String> permissions = getPermissionsByUsername(username);


        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 1. 从主体获取提交的认证信息
        String username = (String) authenticationToken.getPrincipal();

        // 1. 从数据库验证(模拟)
        String password = getPasswordByUsername(username);
        if(password == null) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, "customRealm");
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("zaq"));
        return simpleAuthenticationInfo;
    }

    private String getPasswordByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        if(user != null) {
            return user.getPassword();
        }
        return null;
    }

    private Set<String> getRolesByUsername(String username) {
        System.out.println("从数据库中获取授权信息");
        List<String> list = userDao.getRolesByUsername(username);
        Set<String> roles = new HashSet<String>(list);
        return roles;
    }

    private Set<String> getPermissionsByUsername(String username) {
        List<String> list = userDao.getPermissionsByUsername(username);
        Set<String> permissions = new HashSet<String>(list);
        return permissions;
    }


    public static void main(String args[]) {
//        Md5Hash md5Hash = new Md5Hash("123");
        Md5Hash md5Hash = new Md5Hash("123", "zaq");
        System.out.println(md5Hash.toString());
    }

}
