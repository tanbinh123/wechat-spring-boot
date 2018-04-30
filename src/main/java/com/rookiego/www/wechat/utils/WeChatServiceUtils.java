package com.rookiego.www.wechat.utils;

/**
 * <b>类名称:</b>WeChatServiceUtils<br/>
 * <b>类注释:</b>微信公众平台接口实现<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月30日 15时45分19秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
public class WeChatServiceUtils {

    public static String getAccessToken(String appId, String secret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;
        String resultMsg = HttpClientHelper.sendPost(url);
        return resultMsg;
    }

}
