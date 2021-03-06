package designModel.结构型模式.代理模式.动态代理.jdk;

import sun.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StudentInnovactionHandle implements InvocationHandler{
    private  Object target;

    public StudentInnovactionHandle(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("学习前要做的事。。。。");
        //这句是重点，注意不是反射调用proxy的方法，而是反射直接调用target的方法。
        // 这里传入的proxy实际是动态生成的那个class对象，里面有InvocationHandler的一个引用。这里没有用处。
        Object result = method.invoke(this.target,args);
        System.out.println("学习后要做的事。。。");
        return result;
    }

}
