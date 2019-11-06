package com.baizhi.test;

import com.baizhi.Application;
import com.baizhi.dao.Userdao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//开启Spring的注解版测试
@RunWith(SpringRunner.class)
//作用:读取spring的配置文件自动创建Spring工厂
@SpringBootTest(classes = Application.class)
public class test {
    @Autowired
    private UserService userService;
    @Autowired
    private Userdao userdao;
    @Test
    public void test1(){
        for (int i=1;i<10;i++){
            List<User> users = userService.showAll();
            System.out.println(users);
        }
    }
    @Test
    public void test2(){
        List<User> users = userdao.showAll();
        System.out.println(users);
    }
    @Test
    public void test3(){

    }
}
