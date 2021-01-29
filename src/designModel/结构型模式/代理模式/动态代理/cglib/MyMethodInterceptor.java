package designModel.结构型模式.代理模式.动态代理.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置事件....");
        //重点，注意这里是invokeSuper,而不是invoke
        methodProxy.invokeSuper(o,objects);
        System.out.println("后置事件....");
        return methodProxy;
    }
}
