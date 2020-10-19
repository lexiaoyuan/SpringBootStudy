package com.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 3. 创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro的内置过滤器
        /**
         * anon: 无需认证就可以访问
         * authc: 必须认证了才能访问
         * user: 必须有记住我功能才能访问
         * perms: 拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        // 登录拦截
        Map<String, String> filterMap = new LinkedHashMap<>();


        // 授权规则  （要放在前面）
        filterMap.put("/user/add", "perms[user:add]"); // 有add权限才能访问add页面
        filterMap.put("/user/update", "perms[user:update]"); // 有update权限才能访问update页面

        // 拦截请求
        //filterMap.put("/user/add", "authc");
        //filterMap.put("/user/update", "authc");
        filterMap.put("/user/*", "authc");  // 支持通配符

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        // 设置未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        return shiroFilterFactoryBean;
    }

    // 2. 创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 1. 创建realm对象，需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    // 整合ShiroDialect：用来整合shiro和thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
