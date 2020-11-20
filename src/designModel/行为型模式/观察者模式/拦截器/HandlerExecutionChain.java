package designModel.行为型模式.观察者模式.拦截器;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class HandlerExecutionChain {
    private final Object handler;
    private HandlerInterceptor[] interceptors;
    private List<HandlerInterceptor> interceptorList;

    public HandlerExecutionChain(Object handler) {
        this(handler, (HandlerInterceptor[])null);
    }

    public HandlerExecutionChain(Object handler, HandlerInterceptor[] interceptors) {
        if (handler instanceof HandlerExecutionChain) {
            HandlerExecutionChain originalChain = (HandlerExecutionChain)handler;
            this.handler = originalChain.getHandler();
            this.interceptorList = new ArrayList();
            CollectionUtils.mergeArrayIntoCollection(originalChain.getInterceptors(), this.interceptorList);
            CollectionUtils.mergeArrayIntoCollection(interceptors, this.interceptorList);
        } else {
            this.handler = handler;
            this.interceptors = interceptors;
        }

    }

    public Object getHandler() {
        return this.handler;
    }

    public void addInterceptor(HandlerInterceptor interceptor) {
        this.initInterceptorList();
        this.interceptorList.add(interceptor);
    }

    public void addInterceptors(HandlerInterceptor[] interceptors) {
        if (interceptors != null) {
            this.initInterceptorList();
            this.interceptorList.addAll((Collection)Arrays.asList(interceptors));
        }

    }

    private void initInterceptorList() {
        if (this.interceptorList == null) {
            this.interceptorList = new ArrayList();
        }

        if (this.interceptors != null) {
            this.interceptorList.addAll((Collection)Arrays.asList(this.interceptors));
            this.interceptors = null;
        }

    }

    public HandlerInterceptor[] getInterceptors() {
        if (this.interceptors == null && this.interceptorList != null) {
            this.interceptors = (HandlerInterceptor[])this.interceptorList.toArray(new HandlerInterceptor[this.interceptorList.size()]);
        }
        java.util.Arrays.sort(this.interceptors, new HandlerInterceptorComparator());
        return this.interceptors;
    }

    @Override
    public String toString() {
        if (this.handler == null) {
            return "HandlerExecutionChain with no handler";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("HandlerExecutionChain with handler [").append(this.handler).append("]");
            if (!CollectionUtils.isEmpty(this.interceptorList)) {
                sb.append(" and ").append(this.interceptorList.size()).append(" interceptor");
                if (this.interceptorList.size() > 1) {
                    sb.append("s");
                }
            }

            return sb.toString();
        }
    }
}
