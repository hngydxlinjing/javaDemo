package jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 枚举的用法(穷举，无法实例化枚举类型，1.5开始引入，相当于jvm初始化创建了该对象，然后放在一个enum中管理)
 * 1、当作常量集合 ：常量分组
 * 2、当作switch
 * 3、单例 ：如果打算自定义自己的方法，那么必须在enum实例序列的最后添加一个分号。而且 Java 要求必须先定义 enum 实例。 且枚举类型的构造方法必须为私有方法。
 */
public class   TestEnum {
    @Test
    public void  test1(){
        System.out.println(MyDay.MONDAY.getCode());
        System.out.println(MyDay.MONDAY.getName());
        System.out.println(MyDay.THUSDAY.getCode());
        System.out.println(MyDay.THUSDAY.getName());
        System.out.println(MyDay.THUSDAY);
    }

    @Test
    public void test2() {
        MyDay newDay = MyDay.MONDAY;
        switch (newDay) {
            case MONDAY:
                System.out.println("MONDAY");
                break;
            case THUSDAY:
                System.out.println("THUSDAY");
                break;
        }
    }

    @Test
    public void test3() {
        SingletonEnum singletonEnum = SingletonEnum.ONLYONE;
        System.out.println(singletonEnum.toString());
    }

}


enum MyDay {

    MONDAY(1,"星期一"),THUSDAY(2,"星期二");//这个后面必须有分号

    private int code;
    private String name;
    private MyDay(int code,String name) {
        this.code = code;
        this.name = name();
    }

    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }

}

enum SingletonEnum {

    ONLYONE;//这个后面必须有分号

    private SingletonEnum() {
        System.out.println("初始化单例对象。。。。");

    }

}
