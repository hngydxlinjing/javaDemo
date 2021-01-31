package designModel.创建型模式.单例模式.枚举类加载;

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
    DATASOURCE;
    private DBConnection connection = null;
    private DatasourceEnum() {
        connection = new DBConnection();
    }
    public DBConnection getConnection() {
        return connection;
    }
    public class DBConnection {}
    public static void main(String[] args) {
        DBConnection con1 = DatasourceEnum.DATASOURCE.getConnection();
        DBConnection con2 = DatasourceEnum.DATASOURCE.getConnection();
        System.out.println(con1 == con2);
    }
}