package io.readproperties;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
public class PropertiesDemo01 {
    /**
     * 读取解析properties文件
     */
    @Test
    public  void readProperties() {
        //java.util包下专门做properties文件解析的类=》Properties
        Properties properties = new Properties();
        File file = new File("E:\\data\\0-108-产品代码变更\\dao.properties");
        //判断文件是否存在
        if(file.exists()) {
            InputStream inStream = null;
            try {
                inStream = new FileInputStream(file);
                //通过调用Properties的load方法，实现文件的加载、解析
                properties.load(inStream);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(inStream != null) {
                    try {
                        inStream.close();//关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String newFundCode =  "GR977A";
            String oldFundCode = "SGR977";
            Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            for (Map.Entry<Object, Object> map:
                    entries) {
                String key  = map.getKey().toString();
                String[] dinfine =key.split("\\.",-1);
                String user = dinfine[0];
                String tablename = dinfine[1];
                String columnname = dinfine[2];
                if(user.equalsIgnoreCase("APP_OMS") || user.equalsIgnoreCase("APP_MOT")  ){
                    continue;
                }
                if(columnname.equalsIgnoreCase("C_MAINFUNDCODE") || columnname.equalsIgnoreCase("C_FORMALFUNDCODE")){
                    continue;
                }
                if(tablename.matches(".*[0-9]{1,}.*") || tablename.endsWith("BAK") || tablename.endsWith("bak")){
                    continue;
                }
                //System.out.println("update "+ user+"."+tablename + "   set  "+columnname+"  ='"+ newFundCode +"'  where "+columnname +" = '"+oldFundCode+"';");
                System.out.println( user+"."+tablename );
            }

        }else {
            System.out.println("properties文件未找到，请核对提供文件路径！");
        }
    }

}