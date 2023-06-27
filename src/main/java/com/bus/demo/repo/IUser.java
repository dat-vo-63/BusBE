package com.bus.demo.repo;

import com.bus.demo.entity.User;

public interface IUser {
public User findByPhoneNumber(String phoneNumber);
public User save(User user);
//public User 
}
