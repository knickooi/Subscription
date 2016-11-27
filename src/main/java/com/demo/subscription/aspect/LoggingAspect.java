package com.demo.subscription.aspect;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.demo.subscription.repo.MessageTypeRepository;
import com.demo.subscription.repo.SubscriptionRepository;

import lombok.val;

@Component
@Aspect
public class LoggingAspect {
	
	@Around("execution(* com.demo.subscription.repo.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Log log = null;
		
		if (joinPoint.getTarget() instanceof MessageTypeRepository) {
	    	val cls = Class.forName(Constants.CLASS_MESSAGE_TYPE_REPOSITORY);
	    	log = LogFactory.getLog(cls);
	    } else if (joinPoint.getTarget() instanceof SubscriptionRepository) {
	    	val cls = Class.forName(Constants.CLASS_SUBSCRIPTION_REPOSITORY);
	    	log = LogFactory.getLog(cls);
	    }
		
		if (log == null)
			return joinPoint.proceed();
		
        String methodAndArguments = getMethodAndArgumentsAsString(joinPoint);
        
//        log.info(methodAndArguments);
        
        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.error(methodAndArguments + getExceptionAsString(ex, getDuration(start)), ex);
            throw ex;
        }
        log.info(methodAndArguments + getResultAsString(result, getDuration(start)));
        return result;
    }
	
	protected long getDuration(long start) {
        return System.currentTimeMillis() - start;
    }

    protected String getMethodAndArgumentsAsString(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs()).map(arg -> arg.toString())
                .collect(Collectors.joining(", ", getMethodName(joinPoint) + "(", ")"));
    }

    protected String getMethodName(ProceedingJoinPoint joinPoint) {
        return MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
    }

    protected String getResultAsString(Object result, long duration) {
        return new StringBuilder(" returned ").append(result).append(" in ").append(duration).append(" ms")
                .toString();
    }

    protected String getExceptionAsString(Throwable ex, long duration) {
        return new StringBuilder(" threw ").append(ex.getClass().getSimpleName()).append(" after ").append(duration)
                .append(" ms with message ").append(ex.getMessage()).toString();
    }
}
