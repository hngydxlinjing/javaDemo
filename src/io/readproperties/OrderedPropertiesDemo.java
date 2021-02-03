package io.readproperties;

import org.junit.Test;
import util.String.StringUtils;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class OrderedPropertiesDemo {

    @Test
    public void demo() {
        Properties prop = new OrderedProperties();
        String filepath = "E:\\data\\01-任务\\Doing_1_112-产品代码变更\\TA部分的代码\\ppos_昊晟天成2号私募证券投资基金.txt";
        String oldFundCode = "昊晟天成2号私募证券投资基金";
        String newFundCode = "昊晟天成2号私募证券投资基金B类";
        try {
            InputStreamReader iStreamReader = new InputStreamReader(new FileInputStream(filepath), "utf-8");
            prop.load(iStreamReader);
            Iterator<String> it = prop.stringPropertyNames().iterator();

            File file = new File(filepath);
            String path = file.getParent();
            String filename = file.getName().replace(".txt",".sql");

            File file1 = new File(path+"\\"+filename);
            if(file1.exists()){
                file1.delete();
            }

            while (it.hasNext()) {
                String key = it.next();
                String[] dinfine = key.split("\\.", -1);
                String user = dinfine[0];
                String tablename = dinfine[1];
                String columnname = dinfine[2];
                if (user.equalsIgnoreCase("APP_OMS") || user.equalsIgnoreCase("APP_MOT")) {
                    continue;
                }
                if (columnname.equalsIgnoreCase("C_MAINFUNDCODE") || columnname.equalsIgnoreCase("C_FORMALFUNDCODE")) {
                    continue;
                }
                //包含数字 .*[0-9]{1,}.*
                // 以数字结尾 ^.+?\\d$
                if (tablename.matches("^.+?\\d$") || tablename.endsWith("BAK") || tablename.endsWith("bak")) {
                    continue;
                }

                System.out.println("update " + StringUtils.appentStr4Length((user + "." + tablename), (65)) + "   set  " + StringUtils.appentStr4Length((columnname), 32) + "  =replace(" + StringUtils.appentStr4Length(columnname + ",'" + oldFundCode + "','" + newFundCode + "')", (40 + oldFundCode.length() + newFundCode.length())) + StringUtils.appentStr4Length("  where " + columnname + " like '%" + oldFundCode + "%';", (45 + oldFundCode.length())));
                writtotxt(path,filename,user,tablename,columnname,oldFundCode,newFundCode);
                //  System.out.println( user+"."+tablename +"."+columnname+" = "+prop.get(key));

            }
            iStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




         //如何对齐？ 方案一是每一列后都加个tab键，但是还是有对不齐的清空，需完善
        public void writtotxt(String path,String filename,String user,String tablename,String columnname,String oldFundCode,String newFundCode) throws IOException {
            File file = new File(path);
            File file1 = new File(path+"\\"+filename);

            if(!file1.exists()){
                file1.createNewFile();
                System.out.println("文件创建成功！");
            }


            // filename指定默认的名字
            BufferedOutputStream buff  = new BufferedOutputStream(new FileOutputStream(file1,true));
            String tab = "\t";
            String enter = "\n";
            StringBuffer write = new StringBuffer();
            try {
                write.append(StringUtils.appentStr4Length("update",4));
                write.append(tab);
                write.append(StringUtils.appentStr4Length((user + "." + tablename),user.length()+32));
                write.append(tab);
                write.append(StringUtils.appentStr4Length("set",10));
                write.append(tab);
                write.append(StringUtils.appentStr4Length((columnname), 32));//账号
                write.append(tab);
                write.append("=replace(" + StringUtils.appentStr4Length(columnname + ",'" + oldFundCode + "','" + newFundCode + "')", (40 + oldFundCode.length() + newFundCode.length())));
                write.append(tab);
                write.append(StringUtils.appentStr4Length(tab+"where"+ tab+ columnname + tab+"like"+tab+"'%" + oldFundCode + "%';", (45 + oldFundCode.length())));//账户类型
                write.append(enter);
                    buff.write(write.toString().getBytes("UTF-8"));
                    buff.flush();
                    buff.close();

            } finally{
                try {
                    buff.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

}