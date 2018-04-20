package com.rookiego.www.wechat.controller.message;

import com.alibaba.fastjson.JSONObject;
import com.rookiego.www.wechat.BasicController;
import com.rookiego.www.wechat.dao.WeChatMessageDAO;
import com.rookiego.www.wechat.domain.internal.ReceiveMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("receiveMessage")
public class ReceiveMessageController extends BasicController {

    @Resource
    private WeChatMessageDAO weChatMessageDAO;

    @RequestMapping("list")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        return "messageManage/list.html";
    }


    @ResponseBody
    @RequestMapping("listData")
    public String listData(HttpServletRequest request, HttpServletResponse response) {
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("limit"));
        int pageNum = (page - 1) * size;
        List<ReceiveMessage> receiveMessageList = weChatMessageDAO.queryListPage(pageNum, size);
        int totalCount = weChatMessageDAO.queryAllCount();

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", totalCount);
        map.put("data", receiveMessageList);

        return JSONObject.toJSONString(map);
    }

}
