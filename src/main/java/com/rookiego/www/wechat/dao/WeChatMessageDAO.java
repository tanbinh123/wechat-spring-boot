package com.rookiego.www.wechat.dao;

import com.rookiego.www.wechat.domain.internal.ReceiveMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface WeChatMessageDAO {
    @Insert("insert into rookie_received_message (msg_id,msg_type,msg_content,open_id,create_time) " +
            "values (#{MsgId},#{MsgType},#{msgContent},#{FromUserName},#{createTime})")
    int insertReceivedMessage(Map<String, Object> map);

    @Select("select * from rookie_received_message limit #{pageNum},#{size}")
    List<ReceiveMessage> queryListPage(@Param("pageNum") int pageNum, @Param("size") int size);

    @Select("select count(*) from rookie_received_message")
    int queryAllCount();
}
