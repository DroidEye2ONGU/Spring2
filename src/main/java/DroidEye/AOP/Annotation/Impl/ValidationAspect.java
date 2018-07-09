package DroidEye.AOP.Annotation.Impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Component
@Aspect
public class ValidationAspect {

    //@Before("execution(public int DroidEye.AOP.Annotation.Impl.ArithmeticCalculator.*(..))")
    @Before("LoggingAspect.declareJoinPointExpression()")
    public void validateArgs(JoinPoint joinPoint) {
        System.out.println("validate:" + Arrays.asList(joinPoint.getArgs()));
    }

}
