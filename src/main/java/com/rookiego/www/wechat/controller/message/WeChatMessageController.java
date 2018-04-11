package com.rookiego.www.wechat.controller.message;

import com.rookiego.www.wechat.BasicController;
import com.rookiego.www.wechat.domain.external.TextMessage;
import com.rookiego.www.wechat.service.WeChatMessageServiceI;
import com.rookiego.www.wechat.utils.MessageUtils;
import com.rookiego.www.wechat.utils.SHA1;
import com.rookiego.www.wechat.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * <b>类名称:</b>WeChatMessageController<br/>
 * <b>类注释:</b>微信公众平台开发配置验证、收发消息<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月04日 11时48分27秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
@RestController
public class WeChatMessageController extends BasicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatMessageController.class);

    @Resource
    private WeChatMessageServiceI weChatMessageServiceI;

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

            Map<String, Object> map = XmlUtils.getMap(messageContent);
            int row = weChatMessageServiceI.handlerReceiveMessage(messageContent, map);

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName((String) map.get("FromUserName"));
            textMessage.setFromUserName((String) map.get("ToUserName"));
            textMessage.setContent("功能开发中");
            textMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
            textMessage.setMsgType("text");
            String responseMessage = XmlUtils.getXml(textMessage);
            LOGGER.info("message reply to user is " + responseMessage);

            return responseMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
