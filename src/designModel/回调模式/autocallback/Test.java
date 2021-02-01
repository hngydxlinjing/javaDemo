package designModel.回调模式.autocallback;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)//用来加载spring配置
@ContextConfiguration("classpath:applicationContext.xml")//classpath:表示从根路径查找xml文件
public class Test extends  AbstractJUnit4SpringContextTests {

    @org.junit.Test
    /**
     * XXX
     * 获取到[ITestCallBack]的实现类：com.linjing.autocallback.TestCallBackImpl1
     * 后置处理1.。。XXX
     */
    public  void test() {
        Map params = new HashMap();
        params.put("name","XXX");

        doSomeThings(params);

        Map<String, ITestCallBack> callbacks = ImplementUtil.getEnableBeans(ITestCallBack.class, applicationContext);
        Iterator i$ = callbacks.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry<String, ITestCallBack> entry = (Map.Entry)i$.next();
            ITestCallBack callback = (ITestCallBack)entry.getValue();
            callback.afterAddFile(params);
        }
    }

    private static void doSomeThings(Map params) {
        System.out.println(params.get("name"));
    }


}
