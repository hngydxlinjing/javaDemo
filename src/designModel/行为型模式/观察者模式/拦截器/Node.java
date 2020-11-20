package designModel.行为型模式.观察者模式.拦截器;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class Node {
    private String id;
    private String nodeName;

    public Node(String id, String nodeName) {
        this.id = id;
        this.nodeName = nodeName;
    }

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    void exe(){
        System.out.println(nodeName+"已执行");

    }
}
