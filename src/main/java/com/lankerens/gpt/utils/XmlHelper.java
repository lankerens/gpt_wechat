package com.lankerens.gpt.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lankerens.gpt.bean.wx.MessageReply;
import com.lankerens.gpt.bean.wx.MessageXml;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 上午7:44
 */
@Slf4j
public class XmlHelper {

    private static XmlMapper xm = new XmlMapper();


    {
        //反序列化时，若实体类没有对应的属性，是否抛出JsonMappingException异常，false忽略掉
        xm.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //序列化是否绕根元素，true，则以类名为根元素
        xm.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        //忽略空属性
        xm.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，
        xm.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        //设置转换模式
        xm.enable(MapperFeature.USE_STD_BEAN_NAMING);
    }


    /**
     * xml转java对象
     *
     * @param xmlData
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T xmlToBean(String xmlData, Class<T> clazz) throws Exception {
        return xm.readValue(xmlData, clazz);
    }


    /**
     * 将bean转为xml字符串，bean需要配置注解@JacksonXmlProperty等。
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static String beanToXmlStr(Object input) throws Exception {
        return  xm.writeValueAsString(input);
    }


    /**
     * 将bean的xml字符串转为map，bean需要配置注解@JacksonXmlProperty等。
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static Map<String, Object> beanToXmlStrToMap(Object input) throws Exception {
        String xmlStr = xm.writeValueAsString(input);
        return xm.readValue(xmlStr, Map.class);
    }





    //region
    static String xml = "<xml>\n" +
            "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
            "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
            "  <CreateTime>1348831860</CreateTime>\n" +
            "  <MsgType><![CDATA[text]]></MsgType>\n" +
            "  <Content><![CDATA[this is a test]]></Content>\n" +
            "  <MsgId>1234567890123456</MsgId>\n" +
            "  <MsgDataId>xxxx</MsgDataId>\n" +
            "  <Idx>xxxx</Idx>\n" +
            "</xml>";
    public static void main(String[] args) {
        try {
            var obj = xm.readValue(xml, MessageXml.class);
            log.warn("{}", obj);

            var mr =new MessageReply();
            log.warn("{}", xm.writeValueAsString(mr));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
//endregion
}
