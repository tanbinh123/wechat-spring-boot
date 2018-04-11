package com.rookiego.www.wechat.utils;

import com.rookiego.www.wechat.domain.external.TextMessage;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    public static final Map<String, Object> getMap(String xml) {
        Map<String, Object> map = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(xml);
            Element rootElement = document.getRootElement();
            List<Element> elementList = rootElement.elements();
            for (Element element : elementList) {
                map.put(element.getName(), element.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static final Object getObject(String xml, Class<?> clazz) {
        Object object = null;
        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            object = clazz.newInstance();
            List<Element> elementList = root.elements();
            for (Element element : elementList) {
                String propertyName = element.getName();
                String propertyValue = element.getText();
                Method method = object.getClass().getMethod("set" + propertyName, String.class);
                method.invoke(object, propertyValue);
            }
        } catch (Exception e) {
            LOGGER.info("happen exception", xml, clazz.getName());
            e.printStackTrace();
        }

        return object;
    }

    public static final String getXml(Object object) {
        try {
            Document document = DocumentHelper.createDocument();
            Element rootElement = document.addElement("xml");
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method method = object.getClass().getMethod("get" + name);
                String value = (String) method.invoke(object);
                if (StringUtils.isEmpty(StringUtils.trim(value))) {
                    Element element = rootElement.addElement(name);
                    element.setText(value);
                }
            }
            return document.asXML();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    public static void main(String[] args) throws DocumentException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String xml = "<xml><ToUserName><![CDATA[gh_ebd6be777936]]></ToUserName>\n" +
                "<FromUserName><![CDATA[oz8xgtx2gCgmlV_9qApnfgi3Nu_8]]></FromUserName>\n" +
                "<CreateTime>1522685944</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[哈喽]]></Content>\n" +
                "<MsgId>6539886331991078023</MsgId>\n" +
                "</xml>";

        Map<String, Object> map = (Map<String, Object>) XmlUtils.getMap(xml);
        System.out.println(map);

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName((String) map.get("FromUserName"));
        textMessage.setFromUserName((String) map.get("ToUserName"));
        textMessage.setContent("功能开发中");
        textMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
        textMessage.setMsgType("text");

        System.out.println(XmlUtils.getXml(textMessage));


    }

}
