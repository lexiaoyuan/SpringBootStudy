package com.springboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 请求授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()   // 首页所有人可以访问
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 开启登录页面，没有权限时默认到登录页面 /login
        // 定制首页：.loginPage("/toLogin")
        // 登录提交表单的请求：.loginProcessingUrl("/login")
        http.formLogin()
                .loginPage("/toLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login");

        // 开启注销功能 .logout()
        // 注销成功跳转到首页 .logoutSuccessUrl("/")
        http.logout().logoutSuccessUrl("/");

        // 使用自定义的login页面后，需要关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
        //http.csrf().disable();

        // 开启记住我
        // 设置提交的记住我的input的name：.rememberMeParameter("remember")，默认是remember-me
        http.rememberMe().rememberMeParameter("remember");
    }

    // 认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 从内存中得到用户及相应的角色。    这些数据应该从数据库中读取
        //Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
        //要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
        //spring security 官方推荐的是使用BCrypt加密方式。
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("lexiaoyuan").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("beta").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
