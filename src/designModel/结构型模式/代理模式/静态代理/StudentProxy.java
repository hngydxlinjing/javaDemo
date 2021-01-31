package designModel.结构型模式.代理模式.静态代理;

public class StudentProxy implements IStudent{
    IStudent student;

    public StudentProxy(IStudent student) {
        this.student = student;
    }

    @Override
    public void study() {
        System.out.println("学习前要做的事。。。。");
        student.study();
        System.out.println("学习后要做的事。。。");
    }
}
