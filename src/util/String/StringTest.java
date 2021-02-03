package util.String;

import org.junit.Test;

public class StringTest {

    @Test
    public void test(){
        String aa = "223";
        String s = StringUtils.rightPad(aa, 12);
        System.out.println(s);
    }
}
