package designModel.创建型模式.单例模式.静态加载;

import designModel.创建型模式.单例模式.SingletonObj;
import designModel.创建型模式.单例模式.饿汉式.HungryMode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @version V1.0
 * @author: zouwh
 * @description: 饿汉式
 * @date: 2021/1/31 2:37 PM
 * @Copyright: 2021 www.ztzqzg.com Ltd. All rights reserved.
 * 注意：本内容仅限于中泰证券（上海）资产管理有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
public class StaticMode {
    //重点，这里可以使用static final ，确保只初始化一次
    private static final SingletonObj hungry  ;
    static {
        hungry = new SingletonObj();
    }
    public StaticMode(){
    }

    private static Object getInstance() {
        return hungry;
    }

    @Test
    public void  test(){
        Object  hugry= StaticMode.getInstance();
    }



}


