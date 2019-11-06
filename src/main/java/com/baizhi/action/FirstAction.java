package com.baizhi.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//这是在github上修改过后的
@Controller
@RequestMapping("/first")
public class FirstAction {
    @RequestMapping("/one")
    public String one(String name,Integer age) throws Exception{
        System.out.println(name);
        System.out.println(age);
        return "index";
    }
}
