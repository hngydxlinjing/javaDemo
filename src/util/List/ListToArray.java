package util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linjing
 * @date: Created in 2020/11/20
 */
public class ListToArray {
    public static void main(String[] args) {
        List<Student> list =new ArrayList<>();
        list.add(new Student("李红"));
        list.add(new Student("小明"));

        Student[] objects = list.toArray(new Student[list.size()]);
    }
}
