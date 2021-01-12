package util.String;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class XssTrip {

    @Test
    public void test01() throws Exception {
        stripXSS(null,"<script>alert(1);</script>success",false);
    }

    @Test
    public void test02() throws Exception {
        stripXSS(null,"success",false);
    }

    public static String stripXSS(String name, String value, boolean excludeMatchLink)  throws  Exception{
        if (StringUtils.isEmpty(value)) {
            return value;
        } else {
            boolean isviewImg = matchInsMul(value, "<img(.*?)src=\"(.*?)/fileController.go\\?viewimg&(.*?)=(.*?)&(.*?)sign=(.*?)\"(.*?)/>");
            if (!excludeMatchLink && !isviewImg) {
                matchInsMulDot(name, value.toLowerCase(), "src[\\s\r\n]*=[\r\n]*\\'(.*?)\\'");
                matchInsMulDot(name, value.toLowerCase(), "src[\\s\r\n]*=[\r\n]*\\\"(.*?)\\\"");
                matchInsMulDot(name, value.toLowerCase(), "src[\\s\r\n]*=[\r\n]*(.*?)");
                matchInsMulDot(name, value.toLowerCase(), "<a(.*?)>");
            }

            matchInsMulDot(name, value.toLowerCase(), "<script>(.*?)</script>");
            matchInsMulDot(name, value.toLowerCase(), "<script(.*?)>");
            matchInsMulDot(name, value.toLowerCase(), "</script(.*?)>");
            matchInsMulDot(name, value.toLowerCase(), "eval\\((.*?)\\)");
            matchInsMulDot(name, value.toLowerCase(), "expression\\((.*?)\\)");
            matchInsMulDot(name, value.toLowerCase(), "javascript:");
            matchInsMulDot(name, value.toLowerCase(), "vbscript:");
            matchInsMulDot(name, value.toLowerCase(), "onload(.*?)=");
            matchInsMulDot(name, value.toLowerCase(), "<iframe>(.*?)</iframe>");
            matchInsMulDot(name, value.toLowerCase(), "<iframe(.*?)>");
            matchInsMulDot(name, value.toLowerCase(), "</iframe(.*?)>");
            matchInsMulDot(name, value.toLowerCase(), "<svg>(.*?)</svg>");
            matchInsMulDot(name, value.toLowerCase(), "<svg(.*?)>");
            matchInsMulDot(name, value.toLowerCase(), "</svg(.*?)>");
            matchInsMulDot(name, value.toLowerCase(), "<img(.*?)src(.*?)=(.*?)onerror(.*?)=(.*?)");
            matchInsMulDot(name, value.toLowerCase(), "<img(.*?)onclick(.*?)=(.*?)");
            matchInsMulDot(name, value.toLowerCase(), "alert\\((.*?)\\)");
            matchInsMulDot(name, value.toLowerCase(), "prompt\\((.*?)\\)");
            matchInsMulDot(name, value.toLowerCase(), "confirm\\((.*?)\\)");
            matchInsMulDot(name, value.toLowerCase(), "document.location(.*?)=");
            matchInsMulDot(name, value.toLowerCase(), "document.title(.*?)=");
            matchInsMulDot(name, value.toLowerCase(), "document.write(.*?)");
            matchInsMulDot(name, value.toLowerCase(), "<img(.*?)onerror(.*?)=(.*?)");
            matchInsMulDot(name, value.toLowerCase(), "utl_inaddr");
            matchInsMulDot(name, value.toLowerCase(), "onmouseover(.*?)=");
            matchInsMulDot(name, value.toLowerCase(), "onmousemove(.*?)=");
            matchInsMulDot(name, value.toLowerCase(), "<iframe(.*?)");
            matchInsMulDot(name, value.toLowerCase(), "String.fromCharCode");
            matchInsMulDot(name, value.toLowerCase(), "class.classLoader\\.");
            matchInsMulDot(name, value.toLowerCase(), "<embed(.*?)src(.*?)=(.*?)");
            return value;
        }
    }

    private static void matchInsMulDot(String name, String value, String regex) throws Exception {
        Pattern scriptPattern = Pattern.compile(regex, 42);
        Matcher matcher = scriptPattern.matcher(value);
        if (matcher.find()) {
            String param = StringUtils.isNotBlank(name) ? name + ":" + value : value;
            log.info("Specific Character Check Info:[" + param + "], regex：" + regex);
            throw new Exception("不被允许输入的内容，包含非法字符！");
        }
    }

    public static boolean matchInsMul(String value, String regex) {
        Pattern scriptPattern = Pattern.compile(regex, 42);
        Matcher matcher = scriptPattern.matcher(value);
        return matcher.find();
    }


}
