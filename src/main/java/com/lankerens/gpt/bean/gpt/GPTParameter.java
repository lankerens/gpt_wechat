package com.lankerens.gpt.bean.gpt;

import com.lankerens.gpt.bean.gpt.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 上午9:36
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GPTParameter {

    private String model = "text-davinci-003";

    // 内容
    private String prompt ;

    private  Integer max_tokens;

    private String user;

    private Integer temperature = 0;

    private String stop;


    private List<ChatMessage> messages;

}
