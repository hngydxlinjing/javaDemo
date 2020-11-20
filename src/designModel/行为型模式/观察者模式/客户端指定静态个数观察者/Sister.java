package designModel.行为型模式.观察者模式.客户端指定静态个数观察者;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class Sister implements Children {
    public void start() {
        System.out.println("姐姐开吃了。。。");
    }

    @Override
    public void fstart(Object obj) {
        if(obj.toString().equals("父亲动了筷子")){
            this.start();
        }
    }
}
