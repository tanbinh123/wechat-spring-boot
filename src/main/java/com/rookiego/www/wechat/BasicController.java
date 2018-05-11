package com.rookiego.www.wechat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by rookie on 2018/3/31.
 */
public class BasicController {

    public void setRequestCharacter(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setResponseCharacter(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
    }

    public String getCookiesString(HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer();

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            stringBuffer.append(cookie.getName());
            stringBuffer.append(":");
            stringBuffer.append(cookie.getValue());
            stringBuffer.append(";");
        }

        return stringBuffer.toString();
    }

    public String getRequestParamsString(HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer();

        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append(":");
            stringBuffer.append(Arrays.toString(entry.getValue()));
            stringBuffer.append(";");
        }

        return stringBuffer.toString();
    }

    public String getValueFromCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public void setCookie(HttpServletResponse response, String name, String value, int effectiveTime, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(effectiveTime);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

}
