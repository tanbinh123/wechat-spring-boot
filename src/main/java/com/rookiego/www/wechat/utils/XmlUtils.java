package com.rookiego.www.wechat.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>类名称:</b>XmlUtils<br/>
 * <b>类注释:</b>XML 解析构建帮助类<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月02日 23时07分08秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
public class XmlUtils {

    public static Map<String, Object> parseXMLString(String xml) {
        Map<String, Object> map = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(xml);
            Element rootElement = document.getRootElement();
            List<Element> elementList = rootElement.elements();
            for (Object object : elementList) {
                Element element = (Element) object;
                map.put(element.getName(), element.getTextTrim());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        String xml = "<xml><ToUserName><![CDATA[gh_ebd6be777936]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oz8xgtx2gCgmlV_9qApnfgi3Nu_8]]></FromUserName>\n" +
                "<CreateTime>1522681521</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[123456]]></Content>\n" +
                "<MsgId>6539867335350727366</MsgId>\n" +
                "</xml>";


        Map<String, Object> map = XmlUtils.parseXMLString(xml);
        System.out.println(map);
    }


}
