package com.rookiego.www.wechat.controller;

import com.rookiego.www.wechat.BasicController;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class TestController extends BasicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("1.gif")
    public void receiveTj(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(getRequestParamsString(request));
        stringBuffer.append(getCookiesString(request));

        //cookie中设置跟踪标识
        String traceId = getValueFromCookie(request, "tj-trace-id");
        if (Strings.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
            setCookie(response, "tj-trace-id", traceId, -1, "/");
        }

        LOGGER.info(stringBuffer.toString());
    }


}
