package designModel.行为型模式.观察者模式.拦截器;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class Request {
    private String  nodeId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Request(String nodeId) {
        this.nodeId = nodeId;
    }
}
