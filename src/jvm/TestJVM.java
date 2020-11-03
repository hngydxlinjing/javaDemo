package jvm;

/**
 * @author linjing
 * @date: Created in 2020/10/15
 * javac 编译成class文件
 * javap -c 将class文件 反汇编  TestJVM.txt
 * javap -v 将class文件 反汇编 附带常量池 DynamicTestJVM.txt
 */
public class TestJVM {
    public static void main(String[] args) {
        int a =1;
        int b =6;
        addMethod(a, b);
    }

     public static int addMethod(int a, int b) {
        return a+b;
    }
}
