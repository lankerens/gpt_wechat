package com.lankerens.gpt.bean.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 上午7:47
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "xml")
@ToString
public class MessageXml {

    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    @JacksonXmlProperty(localName = "CreateTime")
    private String createTime;

    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;

    @JacksonXmlProperty(localName = "Content")
    private String content;

    @JacksonXmlProperty(localName = "MsgId")
    private String msgId;

    @JacksonXmlProperty(localName = "MsgDataId")
    private String msgDataId;

    @JacksonXmlProperty(localName = "Idx")
    private String idx;


    // 关注事件
    // Event	事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    @JacksonXmlProperty(localName = "Event")
    private String event;

}
