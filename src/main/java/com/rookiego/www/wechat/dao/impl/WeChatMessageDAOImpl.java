package com.rookiego.www.wechat.dao.impl;

import com.rookiego.www.wechat.dao.WeChatMessageDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class WeChatMessageDAOImpl implements WeChatMessageDAO {
    private static final String NAME_SPACE = WeChatMessageDAO.class.getName() + ".";

    private SqlSession sqlSession;

    @Override
    public int insertReceivedMessage(Map<String, Object> map) {
        return this.sqlSession.insert(NAME_SPACE + "insertReceivedMessage", map);
    }
}
