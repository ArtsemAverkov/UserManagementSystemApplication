package by.it.academy.UserManagementSystem.aop.loger;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * ServiceLogAspect is an aspect class responsible for logging service methods in the User Management System application.
 * It extends the BaseAspect class to leverage common logging functionality.
 *
 * <p>The class uses AspectJ annotations to define pointcuts for the methods it wants to intercept:
 * - @Pointcut("execution(* by.it.academy.UserManagementSystem.service..*(..))"): The pointcut for service methods.
 *
 * <p>The class logs the following information for service methods:
 * - Before the execution of service methods, it logs method signatures and their arguments.
 * - After the execution of service methods, it logs method signatures, the returned result, and their arguments.
 *
 * <p>The log messages use the predefined logging patterns from the BaseAspect class:
 * - BEFORE_SERVICE_PATTERN: The pattern for logging service method arguments before their execution.
 * - AFTER_SERVICE_PATTERN: The pattern for logging service method results and arguments after their execution.
 */

@Slf4j
@Aspect
@Component
public class ServiceLogAspect extends BaseAspect{
    @Pointcut("execution(* by.it.academy.UserManagementSystem.service..*(..))")
    public void before(){

    }
    @Pointcut("execution(* by.it.academy.UserManagementSystem.service..*(..))")
    public void after(){

    }
    @Before("before()")
    public void logControllersBefore(JoinPoint joinPoint){
        final HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.info(BEFORE_SERVICE_PATTERN,
                joinPoint.getSignature().toShortString(),
                getArgsWhitName(joinPoint));
    }

    @AfterReturning(pointcut = "after()", returning = "result")
    public void logControllerAfter(JoinPoint joinPoint,Object result ){
        final HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info(AFTER_SERVICE_PATTERN,
                joinPoint.getSignature().toShortString(),
                getStringInstanceOf(Optional.ofNullable(result).orElse("")),
                getArgsWhitName(joinPoint));


    }
}
