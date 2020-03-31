package com.bingo.showme.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Lyb
 * @since 2020-01-17
 */
@Aspect
@Component
@Slf4j
public class DebugAspect {

    @Pointcut("execution(* com.bingo.showme.controller.*.*(..))")
    private void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            long end = System.currentTimeMillis();
            log.debug("接口{}，消耗时间：{}ms", pjp.getTarget().toString(), (end - start));
        }
    }
}
