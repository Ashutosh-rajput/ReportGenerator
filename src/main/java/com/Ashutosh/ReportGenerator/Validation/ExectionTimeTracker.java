package com.Ashutosh.ReportGenerator.Validation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;



@Aspect
@Component
public class ExectionTimeTracker {
    Logger logger= LoggerFactory.getLogger(ExectionTimeTracker.class);
    @Around("@annotation(com.Ashutosh.ReportGenerator.Validation.TrackExecutiontime)")
    public Object tracktime(ProceedingJoinPoint pjp) throws Throwable {
        long starttime=System.currentTimeMillis();
        Object obj=pjp.proceed();
        long endtime=System.currentTimeMillis();
        logger.info("Method name "+pjp.getSignature()+"time taken to execute: "+(endtime-starttime));
        return obj;

    }

}
