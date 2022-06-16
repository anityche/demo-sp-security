package com.example.demo.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class TimeTraceAop {

	@Around("execution(* com.example.demo..*Controller.*(..))") // 패키지 하위에 모두 적용
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("Start: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); // 다음 로직으로 넘어간다.
        } finally {
            long timeMs = System.currentTimeMillis() - start;
            log.info("End: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
