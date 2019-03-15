package com.xu.soufang.entity;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.xu.soufang.SoufangProjectApplicationTests;
import com.xu.soufang.service.house.IQiNiuService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class QiNiuServiceTests extends SoufangProjectApplicationTests {
    @Autowired
    private IQiNiuService qiNiuService;

    @Test
    public void testUploadFile(){
        String filePath = "C:\\Users\\WIN10\\Pictures\\Camera Roll\\u=1986179278,1118313821&fm=27&gp=0.jpg";

        File file = new File(filePath);

        Assert.assertTrue(file.exists());

        try {
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }


}
