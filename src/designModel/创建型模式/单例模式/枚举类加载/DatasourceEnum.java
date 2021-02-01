package designModel.创建型模式.单例模式.枚举类加载;

import org.junit.Test;

import java.sql.Connection;

/**
 * @version V1.0
 * @author: zouwh
 * @description: 枚举实现单例，序列化式也不担心会破坏单例，枚举自己处理序列化
 * 传统单例存在的另外一个问题是一旦你实现了序列化接口，那么它们不再保持单例了，因为readObject()方法一直返回一个新的对象就像java的构造方法一样
 * @date: 2021/1/31 6:42 PM
 * @Copyright: 2021 www.ztzqzg.com Ltd. All rights reserved.
 * 注意：本内容仅限于中泰证券（上海）资产管理有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public enum DatasourceEnum {
    /**
     *单例数据源
     */
   dataSourceEnum;

    private DBConnection connection  = null;

    //枚举模式private的构造方法，不用显示声明
    DatasourceEnum() {
        System.out.println("初始化数据源。。。。");
        connection = new DBConnection();
    }

    public DBConnection getConnection() {
        return connection;
    }

    public class DBConnection {}

    public static void main(String[] args) {
        DBConnection datasourceEnum1 = DatasourceEnum.dataSourceEnum.getConnection();
        DBConnection datasourceEnum2 = DatasourceEnum.dataSourceEnum.getConnection();
        System.out.println(datasourceEnum1  ==datasourceEnum2 );
    }


}

