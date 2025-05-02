package com.sk.skala.walktogether.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @Pointcut("execution(* com.sk.skala.walktogether.controller.UserController.updateUser(..))")
    public void userUpdate() {}

   @Before("userUpdate()")
    public void logBeforeUpdate(JoinPoint jp) {
        logger.info("회원정보 수정 메서드 호출");
    }

   @After("userUpdate()")
    public void logAfterUpdate(JoinPoint jp) {
        logger.info("회원정보 수정 메서드 호출 종료");
    }
   @Pointcut("execution(* com.sk.skala.walktogether.controller.WalkRouteController.getAllWalkRoutes(..))")
    public void allWalkRoutes() {}

   @Around("allWalkRoutes()")
    public Object logAroundExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("[Around] {}() 시작", methodName);
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - startTime;
        logger.info("[Around] {}() 종료 - 수행시간 {} ms", methodName, duration);
        return result;
    }
}
