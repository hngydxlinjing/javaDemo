package designModel.行为型模式.观察者模式.拦截器;

import org.springframework.core.Ordered;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public interface HandlerInterceptor extends Ordered {
    boolean preHandle(Request var1, Object var3) throws Exception;


    boolean afterCompletion(Request var1,Object var3, Exception var4) throws Exception;
}
