package com.nay.springboot.Thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nay.springboot.Thymeleafdemo.dao.EmployeeRepopsitory;
import com.nay.springboot.Thymeleafdemo.entity.Employee;
import com.nay.springboot.Thymeleafdemo.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public String listEmployees(@RequestParam(required=false) String name,Model model) {
		List<Employee> emplist=null;
		if(name!=null && name.trim().length()>0) {
			emplist=employeeService.getAllEmployees(name);
		}else {
		
		emplist=employeeService.getAllEmployees();
		}
		
		model.addAttribute("employees", emplist);
		
		return "list-employee";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Employee emp=new Employee();
		model.addAttribute("employee", emp);
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee")Employee employee,BindingResult results) {
		System.out.println(results);
		if(results.hasErrors()) {
			return "employees/employee-form";
		}
		else {
			employeeService.save(employee);
			return "redirect:/employees/list";
		}
		
	}
	
	@GetMapping("/update")
	public String updateEmployee(@RequestParam("id")int id,Model model) {
		System.out.println(id);
		Employee emp=employeeService.findById(id);
		model.addAttribute("employee",emp);
		return "employees/employee-form";
	}

	@GetMapping("/search")
	public String searchEmployees(@RequestParam("searchName")String searchName,Model model) {
		List <Employee> empList=null;
		if(searchName!=null && searchName.trim().length()>0) {
			empList=employeeService.findByName(searchName);
		}else {
			empList=employeeService.getAllEmployees();
		}
		model.addAttribute("employees", empList);
		return "list-employee";
		
	}
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id")int id) {
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
}
