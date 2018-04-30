package com.rookiego.www.wechat.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <b>类名称:</b>HttpClientHelper<br/>
 * <b>类注释:</b>HttpClient帮助类<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月30日 15时48分08秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
public class HttpClientHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientHelper.class);

    public static String sendPost(String url) {
        LOGGER.info("send http post ", url);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String resultMsg = EntityUtils.toString(httpEntity);
            LOGGER.info("receive msg is ", resultMsg);
            return resultMsg;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx4a152626b81b2988&secret=01aec0676a74b1268df498b0eff13c25";
        String result = HttpClientHelper.sendPost(url);
        System.out.println(result);
    }

}
