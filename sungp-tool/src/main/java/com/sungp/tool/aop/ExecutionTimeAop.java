package com.sungp.tool.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author sungp
 * @description: 获取方法的运行时间
 * @date 2022年06月15日 21:17
 */
@Aspect
public class ExecutionTimeAop {

    @Around("@annotation(com.sungp.tool.annotation.ExecutionTime)")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法名称
        String name = joinPoint.getSignature().getName();
        //开始执行方法之前时间
        long before = System.currentTimeMillis();
        //执行方法
        Object proceed = joinPoint.proceed();
        //执行方法之后时间
        long after = System.currentTimeMillis();
        //计算方法执行时间
        System.out.println("---[ "+name+" ]---> " + (after - before) + " ms");
        return proceed;
    }

}
