package designModel.结构型模式.代理模式.动态代理.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class TestCglib {

    @Test
    public void test(){
        IStudent student = new Student();
        Enhancer  enhancer = new Enhancer();

        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();
        enhancer.setSuperclass(student.getClass());
        enhancer.setCallback(myMethodInterceptor);
        IStudent o = (IStudent)enhancer.create();
        o.study();
    }
}
