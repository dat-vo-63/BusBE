package com.bus.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.User;

import jakarta.persistence.Entity;

@Service
public class UserService  implements IUser{
@Autowired
UserRepo repo;
	@Override
	public User findByPhoneNumber(String phoneNumber) {
		
		return repo.findByPhoneNumber(phoneNumber);
	}

	@Override
	public String save(User user) {
		String check = "";
		User user2 = repo.findByPhoneNumber(user.getPhoneNumber());
		User user3 = repo.findByEmail(user.getEmail());
		if(user2== null&& user3 ==null)
		{
		user.setIsActive("active");
		repo.save(user);
		return "Register Successful";
		}
		else if (user3!=null && user2==null)  {
			return check+"Phone Have Exits";
		}
		else if (user3== null && user2 !=null) {
			return check+"Email Have Exits";
		
		}
		else {
			return check+" Phone and Email Have Exits";
		}
		
	}

	@Override
	public String login(String email, String password) {
		User user = repo.findByEmailAndPassword(email, password);
		if(user==null)
		{
			return "Username or Passworđ not correct"; 
		}
		else {
			return user.getUserName();
		}
		 
	}

}
