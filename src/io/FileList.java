package io;

/**
 * @author linjing
 * @date: Created in 2020/7/14
 */
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class FileList {
    @Test
    public  void testAddFileNameComment() throws Exception {
        String dirPath = "E:\\publish\\taLiqNavi\\20201227\\sql\\9_initdata";
        File dir = new File(dirPath);
        if(!dir.exists()){
            throw new Exception("文件目录"+dirPath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+dirPath+"不是路径");

        }

        File[] files=dir.listFiles();
        for(int i=0;i<files.length;i++){
            File file =files[i];
            System.out.println("--"+file.getName());
            //String[] split = file.getName().split(".");
            //System.out.println("select * from "+file.getName().toLowerCase().replaceAll(".sql","") +";");
        }
    }

    @Test
    public void testRenameFile() throws Exception {
        String dirPath = "E:\\data\\01-任务";
        File dir = new File(dirPath);
        if(!dir.exists()){
            throw new Exception("文件目录"+dirPath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+dirPath+"不是路径");

        }
        renameFilePath(dir);
    }

    @Test
    public  void testAtFile() throws Exception {
        String dirPath = "E:\\Factory3\\trsfTA\\trunk\\trsfTA\\sql\\9_init";
        File dir = new File(dirPath);
        if(!dir.exists()){
            throw new Exception("文件目录"+dirPath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+dirPath+"不是路径");

        }

        printFilePath(dir);
    }

    @Test
    public  void  fileStruct() throws Exception {
        String dirPath = "E:\\data\\0-106-资管晚间邮件优化\\导出数据\\20201012";
        File dir = new File(dirPath);
        if(!dir.exists()){
            throw new Exception("文件目录"+dirPath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+dirPath+"不是路径");

        }
        getFileName(dir,"--");
    }

    private static void printFilePath(File file){
        File[] fs = file.listFiles();
        for(File f:fs){
            if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
                printFilePath(f);
            if(f.isFile())		//若是文件，直接打印
                System.out.println("@"+f.getAbsolutePath().replace("user","Administrator"));
        }
    }

    private static void renameFilePath(File file){
        File[] fs = file.listFiles();
        String parentPath = file.getAbsolutePath();
        for(File f:fs){
            if(f.isDirectory())	{
               String newPath  =parentPath + "\\Done"+ f.getName();
               // String newPath  =parentPath + "\\"+ f.getName().replace("DONE","Done");
                File f2 = new File(newPath);
                f.renameTo(f2);
            }

        }
    }


        /**
         *
         * @param file 文件
         * @param c 用于记录制表符
         */
        public static void getFileName(File file, String c){
            /**
             * 如果是文件夹,打印名称(带上制表符)
             */
            if(file.isDirectory()){
                System.out.println(c + file.getName());
            }
            /**
             * 获取所有子文件
             */
            File[] files = file.listFiles();
            for(File f : files){
                /**
                 * 首先加一个制表符
                 */
                String temp = "\t"+c;
                if(f.isDirectory()){
                    /**
                     * 如果是文件夹,则进行递归
                     */
                    getFileName(f, temp);
                } else {
                    /**
                     * 如果不是文件夹,则直接打印
                     */
                    System.out.println(temp + f.getName());
                }
            }
        }


    /**
     * @author linjing
     * @date : Created in 2020/6/18
     */
    public static class PathTest {
        public static void main(String[] args) throws Exception {
            // 第一种：获取类加载的根路径   D:\IDEAWorkspace\hs-bluetooth-lock\src\applications\bluetooth-api\target\classes
            File f = new File(PathTest.class.getResource("/").getPath());
            System.out.println(f);

            // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录D:\\IDEAWorkspace\\hs-bluetooth-lock\\src\\base\\target\\classes\\com\\hs\\lock\\bluetooth\\base\\utils
            File f2 = new File(PathTest.class.getResource("").getPath());
            System.out.println(f2);

            // 第二种：获取项目路径    D:\IDEAWorkspace\hs-bluetooth-lock
            File directory = new File("");// 参数为空
            String courseFile = directory.getCanonicalPath();
            System.out.println("courseFile"+courseFile);

            // 第三种：  file:/D:/IDEAWorkspace/hs-bluetooth-lock/src/applications/bluetooth-api/target/classes/
            URL xmlPath = PathTest.class.getClassLoader().getResource("");
            System.out.println(xmlPath);

            // 第四种： 获取当前工程路径 D:\IDEAWorkspace\hs-bluetooth-lock
            System.out.println(System.getProperty("user.dir"));

            // 第五种：  获取所有的类路径 包括jar包的路径
            System.out.println(System.getProperty("java.class.path"));
        }
    }


    @Test
    public void dealSqlBakFile() throws Exception {
        String dirPath = "E:\\data\\脚本存根";
        File dir = new File(dirPath);

        if(!dir.exists()){
            throw new Exception("文件目录"+dirPath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+dirPath+"不是路径");

        }

        deleteBakFile(dir);

    }


    private static void deleteBakFile(File file){
        File[] fs = file.listFiles();
        for(File f:fs){
            if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
                deleteBakFile(f);
            if(f.isFile()) {        //若是文件，判断后缀是否为".~sql"
                System.out.println(f.getName());
                if(f.getName().endsWith(".~sql")){
                    deleteFile(f);
                }
            }

        }
    }

    private  static  void  deleteFile(File file){
        if(file == null){
            return ;
        }
        file.delete();
    }
}