package DroidEye.AOP.Annotation.Impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //1.创建Spring的IOC容器
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从IOC容器中获取Bean实例
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) applicationContext.getBean("arithmeticCalculatorImpl");
        //3.使用bean
        int result = arithmeticCalculator.add(3, 6);
        System.out.println("result:" + result);

        result = arithmeticCalculator.div(1000, 20);
        System.out.println("result:" + result);

    }
}
