package designModel.行为型模式.观察者模式.动态绑定观察者;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class App {
    public static void main(String[] args) {
        new Father(new Children[]{new Son(),new Sister()}).start();
    }
}
