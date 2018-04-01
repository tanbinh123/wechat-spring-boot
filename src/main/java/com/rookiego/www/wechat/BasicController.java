package com.rookiego.www.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

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

}
