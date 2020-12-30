package io.readproperties;

import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesDemo02 {

    @Test
    public void demo(){
        Properties prop = new OrderedProperties();
        String  filepath = "E:\\data\\0-108-产品代码变更\\jeecg-名称.properties";
        String oldFundCode = "鸿道新能源定增1号私募投资基金";
        String newFundCode =  "鸿道新能源定增1号私募投资基金A类";
        try {
            InputStreamReader iStreamReader = new InputStreamReader(new FileInputStream(filepath),"utf-8");
            prop.load(iStreamReader);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
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
                /*if(tablename.matches(".*[0-9]{1,}.*") || tablename.endsWith("BAK") || tablename.endsWith("bak")){
                    continue;
                }*/
                System.out.println("update "+ user+"."+tablename + "   set  "+columnname+"  =replace("+columnname+",'"+oldFundCode+"','"+ newFundCode +"')  where "+columnname +" like '%"+oldFundCode+"%';");
                // System.out.println( user+"."+tablename );

            }
            iStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}