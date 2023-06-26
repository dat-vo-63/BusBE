package com.bus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
public User findByPhoneNumber(int phoneNumber);
public User findByPhoneNumberAndUserName(int phone,String userName);
}
