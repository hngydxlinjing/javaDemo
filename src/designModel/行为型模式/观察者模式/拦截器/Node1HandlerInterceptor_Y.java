package designModel.行为型模式.观察者模式.拦截器;

import org.springframework.core.Ordered;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class Node1HandlerInterceptor_Y implements HandlerInterceptor {
    @Override
    public boolean preHandle(Request var1, Object var3) throws Exception {
        System.out.println("节点"+var1.getNodeId()+"执行前发邮件。。。");
        return true;
    }


    @Override
    public boolean afterCompletion(Request var1, Object var3, Exception var4) throws Exception {
        System.out.println("节点"+var1.getNodeId()+"执行后发邮件。。。");
        return true;
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
