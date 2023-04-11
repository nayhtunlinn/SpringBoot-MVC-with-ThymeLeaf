package com.nay.springboot.Thymeleafdemo.dao;

import com.nay.springboot.Thymeleafdemo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
}
