package io.readLine;

import org.junit.Test;

import java.io.*;

public class TestFileReadLine {

    @Test
    public void test() throws IOException {
        File file = new File("E:\\data\\01-任务\\Doing_1_112-产品代码变更\\TA部分的代码\\新建文本文档.txt");

        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        String s= null;
        while ((s= reader.readLine()) != null){
            System.out.println(s.length());
        }
        reader.close();
    }
}
