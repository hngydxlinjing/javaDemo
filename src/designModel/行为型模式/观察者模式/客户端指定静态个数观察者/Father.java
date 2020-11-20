package designModel.行为型模式.观察者模式.客户端指定静态个数观察者;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 * 案例：中国传统父亲动筷，子女才能动筷
 */
public class Father {
    private  Children[] childs;
    Father(Children[] childs){
        this.childs = childs;
    }
    void start(){
        System.out.println("父亲开吃了。。。");
        for(Children ch:this.childs){
            ch.fstart("父亲动了筷子");
        }
    }

}
