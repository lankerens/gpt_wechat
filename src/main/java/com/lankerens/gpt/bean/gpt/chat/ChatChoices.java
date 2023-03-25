package com.lankerens.gpt.bean.gpt.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 下午12:46
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatChoices {

    /**
     * "index": 0,
     *      *     "message": {
     *      *       "role": "assistant",
     *      *       "content": "\n\nHello there, how may I assist you today?",
     *      *     },
     *      *     "finish_reason": "stop"
     */
    private Integer index;

    private ChatMessage message;

    private String finish_reason;
}
