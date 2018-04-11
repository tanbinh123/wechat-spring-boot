package com.rookiego.www.wechat.service;

import java.util.Map;

public interface WeChatMessageServiceI {

    int handlerReceiveMessage(String messageContent, Map<String, Object> map);

}
