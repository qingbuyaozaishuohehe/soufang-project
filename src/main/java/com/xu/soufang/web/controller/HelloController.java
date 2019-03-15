package com.xu.soufang.web.controller;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println("测试1:缺省用于加密的是 BCryptPasswordEncoder");
        final String passwordPlainText = "123456";
        final String passwordCypher = encoder.encode(passwordPlainText);
        System.out.printf("密码明文是 : %s\n", passwordPlainText);
        System.out.printf("密码密文是 : %s\n", passwordCypher);
        return passwordCypher;
    }
}
