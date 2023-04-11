package com.nay.springboot.Thymeleafdemo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTest {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "test123";
		String encodedPassword = "$2a$10$8ek3RiI0cGp6yDzP1ImdbeZ1bnr7oeOnMXRFZMx5hFlG8lJiI5wde";

		System.out.println();
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + encodedPassword);
		System.out.println();

		
		boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
		System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
	}

}
