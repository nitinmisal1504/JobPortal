package com.prominent.jobportal.dao;


import com.prominent.jobportal.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
