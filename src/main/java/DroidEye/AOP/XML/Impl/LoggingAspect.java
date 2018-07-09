package DroidEye.AOP.XML.Impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


public class LoggingAspect {

    public void declareJoinPointExpression() {

    }

    public void beforeMethod(JoinPoint joinpoint) {
        String methodName = joinpoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinpoint.getArgs());
        System.out.println("The method "+
            methodName + " begins with " + args);
    }

    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }

    public void afterReturning(JoinPoint joinPoint,Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends" + result);
    }

    @AfterThrowing(value = "declareJoinPointExpression()",
            throwing = "ex")                    // 在目标方法出现异常时,会执行的异常对象的类型的异常
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs exception" + ex);
    }


 /*   *//**
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     * 环绕通知类似于动态代理的全过程:ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值,返回值即为目标方法的返回值
     *//*
    @Around("execution(public int DroidEye.AOP.Annotation.Impl.ArithmeticCalculator.*(..))")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {

        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        //执行目标方法
        try {
            //前置通知
            System.out.println("The method " + methodName + " " +
                    "begins with " + Arrays.asList(proceedingJoinPoint.getArgs()));
            result = proceedingJoinPoint.proceed();
            //返回通知
            System.out.println("The method" + methodName + " ends with " + result);
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("The method " + methodName + " occurs exception: " + throwable);
            throwable.printStackTrace();
            //当出现异常时,result的值还为null,程序继续向下执行,
            // 当返回的result转换成int类型时又会发生错误,因此在
            // 这里捕获异常时直接将其抛出
            throw new RuntimeException(throwable);
        }
            //后置通知
        System.out.println("The method " + methodName + " ends");
        return result;
    }*/

}
