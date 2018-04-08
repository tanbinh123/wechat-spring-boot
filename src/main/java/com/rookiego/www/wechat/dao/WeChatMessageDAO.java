package com.rookiego.www.wechat.dao;

import java.util.Map;

public interface WeChatMessageDAO {
    int insertReceivedMessage(Map<String, Object> map);
}
