package com.wind4869.snippets.research.sping;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * TestAspect
 *
 * @author wind4869
 * @since 1.0.0
 */
@Slf4j
@Aspect
@Component
public class TestAspect {
    @Before("@annotation(TestAnnotation)")
    public void log(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String argumentMessage = generateArgumentMessage(
                methodSignature.getParameterNames(),
                joinPoint.getArgs()
        );

        log.info("{}.{}({})", className, methodName, argumentMessage);
    }

    private String generateArgumentMessage(String[] argumentNames, Object[] argumentValues) {
        StringBuilder argumentMessage = new StringBuilder();
        for (int i = 0; i < argumentNames.length; i++) {
            argumentMessage
                    .append(argumentNames[i])
                    .append("=")
                    .append(argumentValues[i]);
        }
        return argumentMessage.toString();
    }
}
