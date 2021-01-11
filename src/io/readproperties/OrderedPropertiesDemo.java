package io.readproperties;

import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class OrderedPropertiesDemo {

    @Test
    public void demo(){
        Properties prop = new OrderedProperties();
        String  filepath = "E:\\data\\0-109-产品代码变更-NBF035\\NBF035产品名称 - 复核.txt";
        String oldFundCode = "私募工场查理投资一丁雪球私募证券投资基金";
        String newFundCode =  "私募工场查理投资一丁雪球私募证券投资基金A类";
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
                //包含数字 .*[0-9]{1,}.*
                // 以数字结尾 ^.+?\\d$
                if(tablename.matches("^.+?\\d$") || tablename.endsWith("BAK") || tablename.endsWith("bak")){
                    continue;
                }
                System.out.println("update "+ user+"."+tablename + "   set  "+columnname+"  =replace("+columnname+",'"+oldFundCode+"','"+ newFundCode +"')  where "+columnname +" like '%"+oldFundCode+"%';");
                //  System.out.println( user+"."+tablename +"."+columnname+" = "+prop.get(key));

            }
            iStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}