package com.baizhi.service;

import com.baizhi.dao.Userdao;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private Userdao userdao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> selectAll(Integer page,Integer rows) {
        List<User> users = userdao.selectAll(page,rows);
        return users;
    }

    @Override
    public void add(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        userdao.add(user);
    }

    @Override
    public void del(String id) {
        userdao.del(id);
    }

    @Override
    public void update(User user) {
        userdao.update(user);
    }

    @Override
    public void dels(String[] ids) {
        userdao.dels(ids);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User selectOne(String id) {
        User user = userdao.selectOne(id);
        return user;
    }

    @Override
    public Integer selectCount() {
        Integer count=userdao.selectCount();
        return count;
    }

    @Override
    public User login(User user) {
        User login = userdao.login(user);
        if (login!=null){
            return login;
        }else {
            return null;
        }

    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> showAll() {
        List<User> users = userdao.showAll();
        return users;
    }
}
