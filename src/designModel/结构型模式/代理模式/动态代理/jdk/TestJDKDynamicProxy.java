package designModel.结构型模式.代理模式.动态代理.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Slf4j
public class TestJDKDynamicProxy {

    @Test
    public  void test(){
        IStudent student = new Student();
        InvocationHandler h = new StudentInnovactionHandle(student);
        //重点句，注意强转成接口类对象，下一句就可以直接调用方法了。
        IStudent proxyInstance = (IStudent)Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), h);
        proxyInstance.study();

    }

    @Test
    public  void test2(){
        IStudent student = new Student();
        ITeacher teacher = new Teacher();
        //1.
        InvocationHandler h = new CommonLogInnovactionHandle(student);
        //重点句，注意强转成接口类对象，下一句就可以直接调用方法了。
        IStudent proxyInstance = (IStudent)Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(), h);
        proxyInstance.study();


        //2.
        InvocationHandler h2 = new CommonLogInnovactionHandle(teacher);
        //重点句，注意强转成接口类对象，下一句就可以直接调用方法了。
        ITeacher proxyInstance2 = (ITeacher)Proxy.newProxyInstance(teacher.getClass().getClassLoader(), teacher.getClass().getInterfaces(), h2);
        proxyInstance2.teach();

    }

}
