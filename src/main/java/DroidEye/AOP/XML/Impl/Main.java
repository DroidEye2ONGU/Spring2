package DroidEye.AOP.XML.Impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext-xml.xml");
        //2.从IOC容器中获取Bean实例
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) applicationContext.getBean("arithmeticCalculator");
        //3.使用bean
        int result = arithmeticCalculator.add(3, 6);
        System.out.println("result:" + result);

        result = arithmeticCalculator.div(1000, 20);
        System.out.println("result:" + result);

    }
}
