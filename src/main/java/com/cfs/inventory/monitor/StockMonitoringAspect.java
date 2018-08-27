package com.cfs.inventory.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cfs.inventory.domain.model.Product;

@Aspect
@Component
public class StockMonitoringAspect {
	
	@After("execution(* com.cfs.inventory.domain.model.Product.deductStock(*))") 
	public void implLogging(JoinPoint joinPoint) {
//		logger.info("Logging: Class - "+ joinPoint.getTarget().getClass()+"; Executing before " + joinPoint.getSignature().getName() + "() method");
		System.out.println("hello");
		System.out.println((Product)joinPoint.getTarget());
	}
}
