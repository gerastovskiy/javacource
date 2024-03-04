package ru.cource.spring.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Aspect
@Component
public class LoggingAspect {
    @Autowired
    Loggerable logger;
    @Around("@annotation(LogTransformation)")
    public Object logArround(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime begin = LocalDateTime.now();

        Object object = joinPoint.proceed();

        LocalDateTime end = LocalDateTime.now();

        StringJoiner joiner = new StringJoiner(",");
        joiner
                .add(begin.toString())
                .add(end.toString())
                .add(joinPoint.getSignature().toShortString())
                .add(String.valueOf(joinPoint.getArgs()))
                .add(String.valueOf(object));

        logger.logData(joiner.toString());

        return object;
    }
}
