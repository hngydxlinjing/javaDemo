package IO;

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
        String dirPath = "C:\\Users\\user\\Documents\\navi\\sql";
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
        }
    }

    @Test
    public  void testAtFile() throws Exception {
        String dirPath = "C:\\Users\\user\\Documents\\navi\\sql";
        File dir = new File(dirPath);
        if(!dir.exists()){
            throw new Exception("文件目录"+dirPath+"不存在");
        }
        if(!dir.isDirectory()){
            throw new Exception("文件目录"+dirPath+"不是路径");

        }

        printFilePath(dir);
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
}