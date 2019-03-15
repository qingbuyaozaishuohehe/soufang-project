package com.xu.soufang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Json;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
/*@Configuration
@ActiveProfiles("test")*/
public class SoufangProjectApplicationTests {

    @Test
    public void contextLoads() {
        Map map = new HashMap();
        map.put("enterpriseId","xxxxxxxxxxxx");
        map.put("page",1);
        map.put("pageSize",1000);
        Object o  = JSONObject.toJSON(map);
        String s = JSONObject.toJSONString(map);

        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        System.out.println(s);
    }

}
