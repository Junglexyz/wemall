/*
package com.jungle.wemall.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/shiro")
    @RequiresPermissions("admin")
    public Object listCategory(){
        return "shiroTest";
    }

    @GetMapping("/log")
    public String log(){
        logger.error("error!");   //error级别的信息，参数就是你输出的信息
        logger.info("我是info信息");    //info级别的信息
        logger.debug("我是debug信息");
        logger.warn("我是warn信息");
        logger.info("认证成功");
        logger.info("{}-{}-{}",2019,11,2);
        return "logTest";
    }

    @GetMapping("/devtools")
//    @RequiresPermissions("noAdmin")
    public Object test(){
        return "devtoolTest";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("登录成功");
        String username = "zhang";
        String password  = "123";
        Subject currentUser = SecurityUtils.getSubject();
        logger.info("login1");
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
            logger.info("login2");

        } catch (UnknownAccountException uae) {

        } catch (LockedAccountException lae) {

        } catch (AuthenticationException ae) {

        }
        return "Hello Spring Boot!登录成功";
    }
}
*/
