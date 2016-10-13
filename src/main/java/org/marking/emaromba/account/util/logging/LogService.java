package org.marking.emaromba.account.util.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogService {
	
	@Pointcut("execution(* org.marking.emaromba.account.controller.*.*(..))")
	private final void controllers() {
		throw new UnsupportedOperationException("Method not avaliable for use");
	}
	
	@Pointcut("execution(* org.marking.emaromba.account.service.*.*(..))")
	private final void services() {
		throw new UnsupportedOperationException("Method not avaliable for use");
	}
	
	@Pointcut("execution(* org.marking.emaromba.account.repository.*.*(..))")
	private final void repositories() {
		throw new UnsupportedOperationException("Method not avaliable for use");
	}
	
	@Pointcut("@annotation(org.marking.emaromba.account.util.logging.Log)")
	private final void annotationLog() {
		throw new UnsupportedOperationException("Method not avaliable for use");
	}
	
	
	@Around("controllers() || services() || annotationLog()")
	public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		
		final Logger logger = LogManager.getLogger(joinPoint.getTarget().getClass());
		
		ThreadContext.put("_originalSourceClassName", joinPoint.getSignature().getDeclaringTypeName());
		ThreadContext.put("_originalSourceMethodName", joinPoint.getSignature().getName());
		
		try {
			logger.info("Enter in " + joinPoint.getSignature().getName());
			Object returned = joinPoint.proceed();
			logger.info("Returned of " + joinPoint.getSignature().getName(), returned);
			
			return returned;
		}
		catch(Throwable e) {
			logger.error("Exception in " + joinPoint.getSignature().getName(), e);
			throw e;
		}	
	}
}
