package designModel.结构型模式.代理模式.动态代理.jdk;

public class Student implements IStudent {
    @Override
    public void study() {
        System.out.println("学习。。。");
    }
}
