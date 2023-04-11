package com.nay.springboot.Thymeleafdemo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nay.springboot.Thymeleafdemo.dao.RoleDao;
import com.nay.springboot.Thymeleafdemo.dao.UserDao;
import com.nay.springboot.Thymeleafdemo.entity.Role;
import com.nay.springboot.Thymeleafdemo.entity.User;
import com.nay.springboot.Thymeleafdemo.user.CrmUser;

@Service
public class UserServiceImpl implements UserService{
	
	private UserDao userDao;

	private RoleDao roleDao;
	

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}
	
	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		// give user default role of "employee"
		//user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
		user.setRoles(Arrays.asList(roleDao.findRoleByName(crmUser.getFormRole())));

		 // save user in the database
		userDao.save(user);
		
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		System.out.println(user.getPassword());
		UserDetails userd=new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
		System.out.println("UserDetails>>>>>>"+userd.getPassword());
		return userd;
	}

	

	

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
