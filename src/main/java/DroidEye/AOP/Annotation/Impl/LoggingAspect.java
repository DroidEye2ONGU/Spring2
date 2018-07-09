package DroidEye.AOP.Annotation.Impl;

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

/**
* 可以使用@Order注解指定切面的优先级,值越小优先级越高
* */

//日志切面
//把这个类声明为一个切面:需要把该类放入到IoC容器中 再声明一个切面
@Aspect
@Component
@Order(2)
public class LoggingAspect {

    /**
     * 定义一个方法,用于声明切入点表达式.一般地,该方法中再不需要添入其他的代码
     * 使用@Pointcut来声明切入点表达式
     * 后面的其他通知直接使用方法名来引用当前的切入点表达式
     * */
    @Pointcut("execution(public int DroidEye.AOP.Annotation.Impl.ArithmeticCalculator.*(..))")
    public void declareJoinPointExpression() {

    }

    // 告诉这个方法 在 哪些类的 哪些方法 开始之前执行?
    //声明该方法是一个前置通知:在目标方法开始之前执行
    //@Before("execution(* DroidEye.AOP.Annotation.Impl.*.*(int,int))")
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinpoint) {
        String methodName = joinpoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinpoint.getArgs());
        System.out.println("The method "+
            methodName + " begins with " + args);
    }

    // 后置通知:在目标方法执行后(无论是否发生异常),执行的通知.
    // 在后置通知中还不能访问目标方法执行的返回值
    //@After("execution(* DroidEye.AOP.Annotation.Impl.*.*(..))")
    @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }
     /* 在方法正常结束后执行的代码
     * 返回通知时可以访问到方法的放回值的
     * */

   //@AfterReturning(value = "execution(public int DroidEye.AOP.Annotation.Impl.ArithmeticCalculator.*(..))",
   //     returning = "result")
   @AfterReturning(value = "declareJoinPointExpression()",
        returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends" + result);
    }

    //异常通知
    /*
     * 在目标方法出现异常时会执行的代码
     * 可以访问到异常对象;且可以指定在出现特定异常时再执行通知代码(通过方法中的一场对象参数类型指定)
     */

    //@AfterThrowing(value = "execution(public int DroidEye.AOP.Annotation.Impl.ArithmeticCalculator.*(..))",
    //        throwing = "ex")
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
