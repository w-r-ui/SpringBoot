package com.baizhi.dao;


import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Userdao {
    List<User> selectAll(@Param("page") Integer page,@Param("rows") Integer rows);

    Integer selectCount();

    void add(User user);
    void del(String id);
    void update(User user);
    void dels(String[] ids);
    User selectOne(String id);
    User login(User user);
    List<User> showAll();
}
