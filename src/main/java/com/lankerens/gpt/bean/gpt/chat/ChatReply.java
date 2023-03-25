package com.lankerens.gpt.bean.gpt.chat;

import com.lankerens.gpt.bean.gpt.GPTUsage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 下午12:45
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatReply {

    /***
     * {
     *   "id": "chatcmpl-123",
     *   "object": "chat.completion",
     *   "created": 1677652288,
     *   "choices": [{
     *
     *   }],
     *   "usage": {
     *     "prompt_tokens": 9,
     *     "completion_tokens": 12,
     *     "total_tokens": 21
     *   }
     * }
     */

    private String id;
    private String object;
//    private List<ChatChoices> choices;
    private ChatChoices[] choices;

    private GPTUsage usage;

}
