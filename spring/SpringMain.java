package spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

import beans.Calculator;

public class SpringMain {
    public static void main(String[] args) throws Exception {
        // Just a tiny represantation of what Spring framework does internally. 
        // It scans the packages for classes and get their names.
        // Then initializes the classes which have specific annotations using reflection. 
        Class c1 = Class.forName("beans.CalculatorService");
        Class a1 = Class.forName("beans.CalculatorAspect");
        
        Object calculatorServiceObj = null;
        Object calculatorAspect = null;

        if (c1.isAnnotationPresent(Component.class)) {
            Constructor beanConst = c1.getDeclaredConstructor();
            calculatorServiceObj = beanConst.newInstance();
        }

        if (a1.isAnnotationPresent(Component.class)) {
            Constructor beanConst = a1.getDeclaredConstructor();
            calculatorAspect = beanConst.newInstance();
        }

        if (a1.isAnnotationPresent(Aspect.class)) {
            calculatorServiceObj = Proxy.newProxyInstance(SpringMain.class.getClassLoader(),
            new Class[] {Calculator.class},
            new SpringInvocationHandler(calculatorAspect));
        }

        Calculator calculator = (Calculator) calculatorServiceObj;
        int res = calculator.sum(2, 1);
        System.out.println(res); // 100
    }
}