package designModel.结构型模式.代理模式.静态代理;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestStaticProxy {
    @Test
    public void test(){
        IStudent student = new Student();
        IStudent studentProxy = new StudentProxy(student);
        studentProxy.study();
    }
}
