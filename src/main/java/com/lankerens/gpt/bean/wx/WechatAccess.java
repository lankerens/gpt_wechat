package com.lankerens.gpt.bean.wx;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上
 * @author: lankerens
 * @time: 2023/3/25 上午7:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatAccess {

    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;

}
