package com.nay.springboot.Thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryRewriter;

import com.nay.springboot.Thymeleafdemo.entity.Employee;

public interface EmployeeRepopsitory extends JpaRepository<Employee, Integer>{
	
	public List<Employee> findAllByOrderByLastNameAsc();

	public List<Employee> findAllByOrderByFirstNameAsc();
	
	public List<Employee> findAllByOrderByEmailAsc();
	
	@Query("FROM #{#entityName} where LOWER(firstName) like %?#{[0].toLowerCase()}% OR LOWER(lastName) like %?#{[1].toLowerCase()}%")
	public List<Employee> findByFirstNameOrLastName(String firstName,String secondName);
	
}
