package com.nay.springboot.Thymeleafdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nay.springboot.Thymeleafdemo.entity.User;
import com.nay.springboot.Thymeleafdemo.user.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}
