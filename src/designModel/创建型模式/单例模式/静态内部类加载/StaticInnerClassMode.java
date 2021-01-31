package designModel.创建型模式.单例模式.静态内部类加载;

import designModel.创建型模式.单例模式.SingletonObj;
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
public class StaticInnerClassMode {
    public StaticInnerClassMode(){
    }

    //重点  类必须是私有，静态的。  变量也必须是私有静态的,最好的是final的
    private  static  class Inner {
        private    static  final  SingletonObj   singletonObj = new SingletonObj();

    }

    private static SingletonObj getInstance() {
        return Inner.singletonObj;
    }


    @Test
    public void  test(){
        SingletonObj singletonObj = StaticInnerClassMode.getInstance();
    }




}



