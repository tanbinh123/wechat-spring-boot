package com.rookiego.www.wechat.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WeChatMessageDAO {
    @Insert("insert into rookie_received_message (msg_id,msg_type,msg_content,open_id,create_time) " +
            "values (#{msgId},#{msgType},#{msgContent},#{openId},#{createTime})")
    int insertReceivedMessage(Map<String, Object> map);
}
