package com.nay.springboot.Thymeleafdemo.dao;

import java.util.List;

import com.nay.springboot.Thymeleafdemo.entity.Employee;

public interface EmployeeDAO {
	
   List<Employee> getAllEmployee();
	
	Employee findById(int id);
	
	Employee save(Employee emp);
	
	void deleteByID(int id);

}
