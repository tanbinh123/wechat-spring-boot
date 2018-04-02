package com.rookiego.www.wechat.controller.message;

import com.rookiego.www.wechat.BasicController;
import com.rookiego.www.wechat.utils.MessageUtils;
import com.rookiego.www.wechat.utils.SHA1;
import com.rookiego.www.wechat.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * Created by rookie on 2018/3/31.
 */
@RestController
public class WeChatMessageController extends BasicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatMessageController.class);

    @GetMapping("weChatMessage")
    public String verifyMessage(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        try {
            String encrypt = SHA1.getSHA1("token", timestamp, nonce, echostr);
            if (encrypt.equals(signature)) {
                return echostr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ERROR";
    }

    @PostMapping("weChatMessage")
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) {
        setRequestCharacter(request);
        setResponseCharacter(response);

        try {
            InputStream inputStream = request.getInputStream();
            String messageContent = MessageUtils.readStreamToString(inputStream);
            LOGGER.info(String.format("%s %s", "messageContent", messageContent));
            Map<String, Object> map = XmlUtils.parseXMLString(messageContent);
            String returnString = "<xml> <ToUserName>< ![CDATA[" + map.get("FromUserName") + "] ]></ToUserName> <FromUserName>< ![CDATA[" + map.get("ToUserName") + "] ]></FromUserName> <CreateTime>" + new Date().getTime() + "</CreateTime> <MsgType>< ![CDATA[text] ]></MsgType> <Content>< ![CDATA[功能开发中...] ]></Content> </xml>";
            LOGGER.info(String.format("%s %s", "returnString", returnString));
            return returnString;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }

}
