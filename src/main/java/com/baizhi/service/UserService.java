package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    List<User> selectAll(Integer page,Integer rows);
    void add(User user);
    void del(String id);
    void update(User user);
    void dels(String[] ids);
    User selectOne(String id);

    Integer selectCount();

    User login(User user);
    List<User> showAll();
}
