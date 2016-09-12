package org.marking.emaromba.account.util.monitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect @Component
public class ReponseTimeMonitorService {
	
	@Around("@annotation(org.marking.emaromba.account.util.monitor.Monitor)")
	public Object logMetricsAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		final Logger LOGGER = LogManager.getLogger(joinPoint.getTarget().getClass());
		final long start = System.nanoTime();
		
		try {
			return joinPoint.proceed();
		}
		catch(Throwable t) {
			throw t;
		}
		finally {
			final long finish = System.nanoTime();
			final long totalInMillis = (finish - start) / 1000000;
			
			LOGGER.trace("Execution of " + joinPoint.getSignature().getName() + " in " + totalInMillis + "ms");
		}	
	}
}


/*
	final GelfConfiguration config = new GelfConfiguration("172.17.0.3", 12201)
		.transport(GelfTransports.UDP)
		.queueSize(1024)
		.reconnectDelay(5000);
	
	GelfMessage message = new GelfMessage("teste");
	message.addAdditionalField("_teste", "teste");
	
	GelfTransports.create(config).send(message);
*/		
