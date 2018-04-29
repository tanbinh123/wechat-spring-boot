package com.rookiego.www.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.rookiego.www.wechat.dao.WeChatMessageDAO;
import com.rookiego.www.wechat.service.WeChatMessageServiceI;
import com.rookiego.www.wechat.utils.SequenceHelper;
import org.apache.commons.lang3.StringUtils;
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
        map.put("msgContent", JSON.toJSONString(map));
        map.put("createTime", new Date());
        if (StringUtils.isEmpty((String) map.get("MsgId"))) {
            map.put("MsgId", SequenceHelper.getNextSequence());
        }

        int row = weChatMessageDAO.insertReceivedMessage(map);
        LOGGER.info("save message from weChat is " + row);

        return row;

    }
}
