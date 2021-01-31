package designModel.创建型模式.单例模式.懒汉式;

import designModel.创建型模式.单例模式.SingletonObj;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @version V1.0
 * @author: zouwh
 * @description: 懒汉式
 * @date: 2021/1/31 5:28 PM
 * @Copyright: 2021 www.ztzqzg.com Ltd. All rights reserved.
 * 注意：本内容仅限于中泰证券（上海）资产管理有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
public class LazyMode {
    private static   SingletonObj singletonObj;



    @Test
    public void test(){
        SingletonObj lazy1 = LazyMode.getInstance();
        SingletonObj lazy2 = LazyMode.getInstance();
        Assert.assertEquals(lazy1,lazy2);

    }

    private static synchronized  SingletonObj getInstance() {
        if(singletonObj == null){
            singletonObj = new SingletonObj();
        }
        return singletonObj;
    }
}
