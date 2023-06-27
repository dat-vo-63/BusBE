package com.bus.demo.repo;

import com.bus.demo.entity.User;

public interface IUser {
public User findByPhoneNumber(String phoneNumber);
public String save(User user);
public String login(String userName,String password);
//public User 
}
