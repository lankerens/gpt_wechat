package com.lankerens.gpt.utils;

import cn.hutool.json.JSONUtil;
import com.lankerens.gpt.bean.gpt.chat.ChatMessage;
import com.lankerens.gpt.bean.gpt.GPTParameter;
import com.lankerens.gpt.bean.gpt.GPTReply;
import com.lankerens.gpt.bean.gpt.chat.ChatReply;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 上午8:54
 */
@Slf4j
public class GPTAPI {

    private static final String API_KEY = "";

    /**
     * curl https://api.openai.com/v1/completions \
     * -H "Content-Type: application/json" \
     * -H "Authorization: Bearer $OPENAI_API_KEY" \
     * -d '{
     * "model": "text-davinci-003",
     * "prompt": "Say this is a test",
     * "max_tokens": 7,
     * "temperature": 0
     * }'
     * <p>
     * <p>
     * {
     * "model": "text-davinci-003",
     * "prompt": "Say this is a test",
     * "max_tokens": 7,
     * "temperature": 0,
     * "top_p": 1,
     * "n": 1,
     * "stream": false,
     * "logprobs": null,
     * "stop": "\n"
     * }
     */
    @Deprecated
    public static String sendMsg(String msg, String fromUserName) {
        String url = "https://api.openai.com/v1/completions";
        var okHttpClient = new OkHttpClient.Builder()
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                .build();

        var gptParameter = new GPTParameter().setModel("text-davinci-003").setPrompt(msg).setMax_tokens(2048)
                .setUser(fromUserName);
        String json = JSONUtil.toJsonStr(gptParameter);
        log.warn("{}", json);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json;charset=utf8"));

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json;charset=utf8")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();


        try {
            var resp = okHttpClient.newCall(request).execute();
            var respJson = resp.body().string();
            var gptReply = JSONUtil.toBean(respJson, GPTReply.class);
            log.warn("gptReply == {}", gptReply.getChoices()[0].getText());

            return gptReply.getChoices()[0].getText();
        } catch (Exception e) {
            log.error("调用GPT出错 == {}", e.getMessage());
            return "调用GPT出错 == " + e.getMessage();
        }
    }


    static Map<String, List<ChatMessage>> map = new HashMap<>();

    public static String sendMsg4(String msg, String fromUserName) {
        String url = "https://api.openai.com/v1/chat/completions";
        var okHttpClient = new OkHttpClient.Builder()
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
//                .retryOnConnectionFailure(true)
//                .connectTimeout(30, TimeUnit.SECONDS) //连接超时
//                .readTimeout(40, TimeUnit.SECONDS) //读取超时
                .build();

        var who = map.getOrDefault(fromUserName, new ArrayList<>());
        if (who.size() > 2) who.clear();
        who.add(new ChatMessage().setRole("user").setContent(msg));
        map.put(fromUserName, who);


        var gptParameter = new GPTParameter().setModel("gpt-3.5-turbo").setMessages(who).setMax_tokens(3500);
        String json = JSONUtil.toJsonStr(gptParameter);
        log.warn("请求: {}", json);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json;charset=utf8"));

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json;charset=utf8")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();
        try {
            var resp = okHttpClient.newCall(request).execute();
            var respJson = resp.body().string();
            var gptReply = JSONUtil.toBean(respJson, ChatReply.class);

//            log.warn("gptReply == {}", respJson);
            log.warn("getContent == {}", gptReply.getChoices()[0].getMessage().getContent());
            return gptReply.getChoices()[0].getMessage().getContent();
        } catch (Exception e) {
            log.error("调用GPT出错 == {}", e.getMessage());
            return "调用GPT出错 == " + e.getMessage();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // 运行时走代理
//        System.out.printf("", sendMsg4("可以写一个Rust版的hello word 吗", "lankerens"));
//        Thread.sleep(1000);
//        System.out.printf("", sendMsg4("可以写一个lua版的hello word 吗", "lankerens"));
        System.out.printf("", sendMsg4("什么是 starrocks", "lankerens"));
//        System.out.printf("", sendMsg4("写一段七言绝句诗，题目是：火锅！",  "lankerens"));
    }

}
