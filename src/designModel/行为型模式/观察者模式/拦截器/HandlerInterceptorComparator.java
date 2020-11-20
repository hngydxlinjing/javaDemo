package designModel.行为型模式.观察者模式.拦截器;

import org.springframework.core.Ordered;

import java.util.Comparator;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class HandlerInterceptorComparator implements Comparator<HandlerInterceptor> {
    @Override
    public int compare(HandlerInterceptor o1,HandlerInterceptor o2) {
        if(o1.getOrder() > o2.getOrder()){
          return 1;
      }else if(o1.getOrder() < o2.getOrder()){
        	return -1;
         	}else{
         		return 0;
         	}
    }

}
