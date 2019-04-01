package com.xu.soufang;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
/*@Configuration
@ActiveProfiles("test")*/
public class SoufangProjectApplicationTests {

    @Test
    public void contextLoads() {
        List<Map> lis = new ArrayList<Map>();
        Map map = new HashMap();
        map.put("enterpriseId","xxxxxxxxxxxx");
        map.put("page",1);
        map.put("pageSize",1000);
        lis.add(map);
        Map map1 = new HashMap();
        map1.put("enterpriseId","fdgdfsfsfdsf");
        map1.put("page",2);
        map1.put("pageSize",3000);
        lis.add(map1);
        try {
            String jsonEntity = new ObjectMapper().writeValueAsString(lis);
            System.out.println(jsonEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
