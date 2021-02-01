package designModel.回调模式.autocallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ImplementUtil {

    public ImplementUtil() {
    }

    public  static  <T> Map<String, T> getEnableBeans(Class<T> clazz, ApplicationContext applicationContext) {
        Map<String, T> beans = new HashMap();
        Map<String, T> allBeans = getBeansOfType(clazz,applicationContext);
        Iterator i$ = allBeans.entrySet().iterator();

        while(i$.hasNext()) {
            Entry<String, T> bean = (Entry)i$.next();
            T e = bean.getValue();
            if (((Implementable)e).isEnable()) {
                beans.put(bean.getKey(), e);
                System.out.println("获取到[" + clazz.getSimpleName() + "]的实现类：" + e.getClass().getName());
            }
        }

        return beans;
    }

    public static  <T> Map<String, T> getBeansOfType(Class<T> type, ApplicationContext applicationContext) {
        Map<String, T> beanMap = applicationContext.getBeansOfType(type);
        if (null == beanMap) {
            beanMap = new HashMap();
        }

        return (Map)beanMap;
    }
}
