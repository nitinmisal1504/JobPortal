package com.prominent.thymeleafdemo01.dao;


import com.prominent.thymeleafdemo01.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
