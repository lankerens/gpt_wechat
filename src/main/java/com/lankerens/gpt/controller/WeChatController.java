package com.lankerens.gpt.controller;


import cn.hutool.crypto.SecureUtil;
import com.lankerens.gpt.bean.wx.MessageReply;
import com.lankerens.gpt.bean.wx.MessageXml;
import com.lankerens.gpt.utils.GPTAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@RequestMapping("api/wx")
@Slf4j
public class WeChatController {

    private static final String TOKEN = "weixinToken";

    private static Map<String, Integer> map = new HashMap<>(8);


    /**
     * 校验
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping
    public String accessToken(@RequestParam("signature") String signature,
                              @RequestParam("timestamp") String timestamp,
                              @RequestParam("nonce") String nonce,
                              @RequestParam("echostr") String echostr) {
        var arr = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        String str = arr[0] + arr[1] + arr[2];
        String hash = SecureUtil.sha1(str);
        if (StringUtils.isNotEmpty(hash) && hash.equals(signature)) {

            return echostr;
        } else {
            log.warn("验证失败");
            return "";
        }
    }



    static Set<String> set = new HashSet<>();
    /**
     * 返回 xml 数据
     *
     * @param mxm
     * @return
     */
    @PostMapping(produces = "application/xml;charset=UTF-8")
    public MessageReply messageHandler(@RequestBody MessageXml mxm) {
        log.warn("接收: {}", mxm.toString());
        if(set.contains(mxm.getMsgId()))  {
            return new MessageReply(mxm.getFromUserName(), mxm.getToUserName(),
                    LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), "text", "is Typing o.0");
        } else {
            var cnt = map.getOrDefault(mxm.getFromUserName(), 0);
            map.put(mxm.getFromUserName(), cnt + 1);
            set.add(mxm.getMsgId());

            if (cnt > 49) {
                return new MessageReply(mxm.getFromUserName(), mxm.getToUserName(),
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), "text", "今天额度已经用完，一天只有50次调用机会");
            } else {
                //       var ans = ChatGPT.sendMsg(mxm.getContent(), mxm.getFromUserName());
                var ans = GPTAPI.sendMsg4(mxm.getContent(), mxm.getFromUserName());

                return new MessageReply(mxm.getFromUserName(), mxm.getToUserName(),
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), "text", ans);
            }
        }
    }


    @Scheduled(cron = "0/5 * * * * ? ")
    public void scheduled() {
        log.info("时间 == {}", LocalDateTime.now());


    }


    @GetMapping("debug/test")
    public String test() {

        return "lankerens";
    }

}


//    @GetMapping("getAccessToken")
//    public void getAccessToken(){
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//
//    }