package com.rookiego.www.wechat.controller.message;

import com.rookiego.www.wechat.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("receiveMessage")
public class ReceiveMessageController extends BasicController {

    @RequestMapping("list")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        return "messageManage/list.html";
    }

}
