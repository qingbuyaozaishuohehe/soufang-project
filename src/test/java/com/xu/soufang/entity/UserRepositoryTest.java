package com.xu.soufang.entity;


import com.xu.soufang.SoufangProjectApplicationTests;
import com.xu.soufang.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


public class UserRepositoryTest extends SoufangProjectApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){


    }

    @Test
    public void md5(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println("测试1:缺省用于加密的是 BCryptPasswordEncoder");
        final String passwordPlainText = "123456";
        final String passwordCypher = encoder.encode(passwordPlainText);
        System.out.printf("密码明文是 : %s\n", passwordPlainText);
        System.out.printf("密码密文是 : %s\n", passwordCypher);

    }
}
