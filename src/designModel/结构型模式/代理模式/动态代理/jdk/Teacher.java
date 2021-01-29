package designModel.结构型模式.代理模式.动态代理.jdk;

public class Teacher implements  ITeacher {
    @Override
    public void teach() {
        System.out.println("教学。。。");
    }
}
