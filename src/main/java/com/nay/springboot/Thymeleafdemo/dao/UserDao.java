package com.nay.springboot.Thymeleafdemo.dao;

import com.nay.springboot.Thymeleafdemo.entity.User;

public interface UserDao {
	
	public User findByUserName(String userName);

	public void save(User user);

}
