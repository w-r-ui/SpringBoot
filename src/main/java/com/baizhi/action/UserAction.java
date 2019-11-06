package com.baizhi.action;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserAction{
    @Autowired
    private UserService userService;
    @RequestMapping("/showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows){
        HashMap<String,Object> maps=new HashMap<>();
        //查询总条数
        Integer count=userService.selectCount();
        //总页数
        Integer pageCount=count%rows==0?count/rows:count/rows+1;
        //当前页的数据
        List<User> users = userService.selectAll(page, rows);
        //设置总条数
        maps.put("records",count);
        //设置总页数
        maps.put("total",pageCount);
        //设置当前页号
        maps.put("page",page);
        //设置当前页数据
        maps.put("rows",users);
        return maps;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public void edit(String oper,User user) throws Exception{
        if ("add".equals(oper)){
            userService.add(user);
        }else if ("edit".equals(oper)){
            userService.update(user);
        }else if ("del".equals(oper)){
            userService.del(user.getId());
        }
    }


    @RequestMapping("/login")
    public String login(User user, HttpSession session) throws Exception{
        User login = userService.login(user);
        if (login!=null){
            session.setAttribute("loginUser",login);
            return "redirect:/userlist.jsp";
        }else {
            return "redirect:/login.jsp";
        }
    }
}
