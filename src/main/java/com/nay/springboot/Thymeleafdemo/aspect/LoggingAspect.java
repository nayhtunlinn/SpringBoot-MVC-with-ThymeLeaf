package com.nay.springboot.Thymeleafdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.nay.springboot.Thymeleafdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.nay.springboot.Thymeleafdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* com.nay.springboot.Thymeleafdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
	private void forAppFlow() {}
	
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		String method=joinPoint.getSignature().toShortString();
		myLogger.info("=======>> in @Before: calling method: "+method);
		
		Object[] o=joinPoint.getArgs();
		for(Object obj:o) {
			myLogger.info("=====>> argument: "+obj);
		}
		
	}
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result"
			)
	public void afterReturning(JoinPoint joinPoint,Object result) {
		
		String method=joinPoint.getSignature().toShortString();
		myLogger.info("=======>> in @AfterReturning: calling method: "+method);
		
		myLogger.info("=====>> result: "+result);
		
	}

}
