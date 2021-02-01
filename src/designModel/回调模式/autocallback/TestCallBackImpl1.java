package designModel.回调模式.autocallback;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestCallBackImpl1 implements ITestCallBack {

    @Override
    public void afterAddFile(Map params) {
        System.out.println("后置处理1.。。"+params.get("name"));
    }

    @Override
    public boolean isEnable() {
        return true;
    }
}
