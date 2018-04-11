package com.rookiego.www.wechat.service.impl;

import com.rookiego.www.wechat.dao.WeChatMessageDAO;
import com.rookiego.www.wechat.service.WeChatMessageServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Component
public class WeChatMessageServiceImpl implements WeChatMessageServiceI {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatMessageServiceImpl.class);

    @Resource
    private WeChatMessageDAO weChatMessageDAO;

    @Override
    public int handlerReceiveMessage(String messageContent, Map<String, Object> map) {
        map.put("msgContent", messageContent);
        map.put("createTime", new Date());

        int row = weChatMessageDAO.insertReceivedMessage(map);
        LOGGER.info("save message from weChat is " + row);

        return row;

    }
}
