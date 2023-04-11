package com.nay.springboot.Thymeleafdemo.service;

import java.util.List;

import com.nay.springboot.Thymeleafdemo.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee findById(int id);

	Employee save(Employee emp);

	void deleteById(int id);

	List<Employee> getAllEmployees(String name);

	List<Employee> findByName(String searchName);
}
