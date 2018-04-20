package com.rookiego.www;

import com.rookiego.www.wechat.WechatSpringBootApplication;
import com.rookiego.www.wechat.dao.WeChatMessageDAO;
import com.rookiego.www.wechat.domain.internal.ReceiveMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WechatSpringBootApplication.class)
public class WechatSpringBootApplicationTests {

    @Resource
    private WeChatMessageDAO weChatMessageDAO;

    @Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("MsgId", "123456");
        map.put("MsgType", "test");
        map.put("msgContent", "{\"name\":\"王鹏程\"}");
        map.put("FromUserName", "21344613sfasfasfa");
        map.put("createTime", "2018-04-11 21:28:00");

        int row = weChatMessageDAO.insertReceivedMessage(map);
        System.out.println(row);
    }

    @Test
    public void testQueryListPage() {
        List<ReceiveMessage> receiveMessageList = weChatMessageDAO.queryListPage(0, 10);
        System.out.println(receiveMessageList.size());
    }

}
