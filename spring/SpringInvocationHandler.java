package spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SpringInvocationHandler implements InvocationHandler {

    private final Object aspect;

    public SpringInvocationHandler(Object aspect) {
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class aspectClass = aspect.getClass();
        Method[] aspectMethods = aspectClass.getDeclaredMethods();

        for (Method m : aspectMethods) {
            if (m.isAnnotationPresent(Around.class)) {
                method.invoke(aspect);
            }
        }
        return null;
    }
}
