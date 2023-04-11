package com.nay.springboot.Thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nay.springboot.Thymeleafdemo.dao.EmployeeRepopsitory;
import com.nay.springboot.Thymeleafdemo.entity.Employee;
import com.nay.springboot.Thymeleafdemo.utils.SortUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
  private EmployeeRepopsitory employeeRepopsitory;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepopsitory employeeRepopsitory) {
		this.employeeRepopsitory=employeeRepopsitory;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepopsitory.findAll();
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		 Optional<Employee> emp=employeeRepopsitory.findById(id);
		 Employee employee=null;
		 if(emp.isPresent()) {
			 employee=emp.get();
		 }
		 else {
			 throw new RuntimeException("Employee Id not found - "+id);
		 }
		 return employee;
	}

	@Override
	public Employee save(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepopsitory.save(emp);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		employeeRepopsitory.deleteById(id);
	}

	@Override
	public List<Employee> getAllEmployees(String name) {
		// TODO Auto-generated method stu
		int num=Integer.parseInt(name);
		switch(num) {
		case SortUtils.FIRST_NAME:return employeeRepopsitory.findAllByOrderByFirstNameAsc();
		case SortUtils.LAST_NAME:return employeeRepopsitory.findAllByOrderByLastNameAsc();
		case SortUtils.EMAIL:return employeeRepopsitory.findAllByOrderByEmailAsc();
		default:return employeeRepopsitory.findAll();
		}
	
	}

	@Override
	public List<Employee> findByName(String searchName) {
		// TODO Auto-generated method stub
		return employeeRepopsitory.findByFirstNameOrLastName(searchName,searchName);
	}

}
