package com.springboot.config;

import com.springboot.entity.User;
import com.springboot.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

// 自定义的Realm，extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了===》授权doGetAuthorizationInfo");

        // 进入这个方法就添加权限给用户
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermission("user:add");

        // 拿到当前的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();  // 拿到下面认证方法中传上来的user对象

        // 添加当前用户的权限，通过数据库得到当前用户的权限
        authorizationInfo.addStringPermission(currentUser.getPerms());

        return authorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了===》认证doGetAuthorizationInfo");

        // 用户名、密码认证，从数据库取
        /*String username = "lexiaoyuan";
        String password = "123456";*/

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        User user = userService.queryByName(userToken.getUsername());

       /* if (!userToken.getUsername().equals(username)) {
            return null;   // 会抛出异常 UnknownAccountException
        }*/

        if (user == null)  // 没有该用户（名）
           return null;  // 会抛出异常 UnknownAccountException

        // 密码认证，shiro做
        //return new SimpleAuthenticationInfo("",password,"");
        // 可以加密：MD5、MD5盐值加密
        //return new SimpleAuthenticationInfo("",user.getPwd(),"");

        // 把从数据库中查到的user传递到上面授权的方法
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
