//package com.sk.skala.walktogether.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAspect {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Pointcut("execution(* com.sk.skala.myapp.controller.PlayerController.getAllPlayers(..))")
//    public void getAllPlayers(){}
//
//    @Pointcut("execution(* com.sk.skala.myapp.controller.PlayerController.updatePlayer(..))")
//    public void updatePlayer(){}
//
//    @Before("getAllPlayers()")
//    public void logGetAllPlayersBefore(JoinPoint joinPoint){
//        logger.info("getAllPlayers 메서드 호출");
//    }
//
//    @After("getAllPlayers()")
//    public void logGetAllPlayersAfter(JoinPoint joinPoint){
//        logger.info("getAllPlayers 메서드 종료");
//    }
//
//    @Before("updatePlayer()")
//    public void logUpdatePlayerBefore(JoinPoint joinPoint){
//        logger.info("updatePlayer 메서드 호출");
//    }
//
//    @After("updatePlayer()")
//    public void logUpdatePlayerAfter(JoinPoint joinPoint){
//        logger.info("updatePlayer 메서드 종료");
//    }
//    @Around("execution(* com.sk.skala.myapp.controller.PlayerController.buyStock(..))")
//    public Object logAroundBuyStock(ProceedingJoinPoint joinPoint) throws Throwable {
//        String methodName = joinPoint.getSignature().getName();
//        logger.info("[Around-Before] {}() 시작", methodName);
//        long startTime = System.currentTimeMillis();
//
//        Object result = joinPoint.proceed();
//
//        long endTime = System.currentTimeMillis();
//        logger.info("[Aroun-After] {}() 종료 - 수행시간 {} ms", methodName, endTime - startTime);
//
//        return result;
//    }
//}
