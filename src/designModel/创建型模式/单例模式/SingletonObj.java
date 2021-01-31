package designModel.创建型模式.单例模式;

/**
 * @version V1.0
 * @author: zouwh
 * @description: 需要要求单例对象
 * @date: 2021/1/31 5:12 PM
 * @Copyright: 2021 www.ztzqzg.com Ltd. All rights reserved.
 * 注意：本内容仅限于中泰证券（上海）资产管理有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class SingletonObj {
    private String name ;

    public SingletonObj(){
        this.name = "我是单例对象";
        System.out.println(this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
