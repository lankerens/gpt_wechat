package com.lankerens.gpt.bean.gpt.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 下午12:39
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChatMessage {

    private String role;

    private String content;

}
