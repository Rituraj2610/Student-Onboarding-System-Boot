package com.rituraj.candidateOnboardingSystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger normalLogger = LoggerFactory.getLogger("NormalLogger");
    private static final Logger exceptionLogger = LoggerFactory.getLogger("ExceptionLogger");
    private static final Logger devLogger = LoggerFactory.getLogger("DeveloperLogger");

    @Pointcut("execution(* com.rituraj.candidateOnboardingSystem.service.*.*(..)) || " +
            "execution(* com.rituraj.candidateOnboardingSystem.controller.*.*(..))")
    public void applicationMethods() {}

    @Around("applicationMethods()")
    public Object logMethodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().toShortString();
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();

        normalLogger.info("Entering method: {}.{}", className, methodName);
        devLogger.debug("Executing {}.{} with args: {}", className, methodName, proceedingJoinPoint.getArgs());

        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed(); // Execute the method
        long executionTime = System.currentTimeMillis() - startTime;

        normalLogger.info("Exiting method: {}.{}, Execution time: {}ms", className, methodName, executionTime);
        devLogger.debug("Completed {}.{}, Result: {}", className, methodName, result);

        return result;
    }

    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().toShortString();
//        String className = joinPoint.getTarget().getClass().getSimpleName();

        exceptionLogger.error("Exception in method: {}, Message: {}",  methodName, exception.getMessage());
        devLogger.error("Exception in {}, Type: {}, StackTrace: ",
                methodName, exception.getClass().getSimpleName(), exception);
    }
}