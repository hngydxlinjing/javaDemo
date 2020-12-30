package util.json;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class TestJson {

    @Test
    public void  testJsonToMap(){
        JSONObject j  = new JSONObject();
        j.put("k1","v1");
        j.put("k2","v2");
        j.put("k3","v3");
        Map<String,Object> map = new HashMap<>();
        Iterator<String> iterator = j.keySet().iterator();
        while(iterator.hasNext())
        {
            String key = (String)iterator.next();
            Object value = j.get(key);
            map.put(key, value);
        }
        log.info(map.toString());
    }

    //读取json文件
    @Test
    public  void readJsonFile() {
        String jsonStr = "";
        try {
            InputStream is=TestJson.class.getResourceAsStream("test/json/testJson.json");
            Reader reader = new InputStreamReader(is,"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonStr = sb.toString();
            log.info(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
