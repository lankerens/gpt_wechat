package com.lankerens.gpt.bean.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 上午10:25
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GPTReply  implements Serializable {

    private String id;

    private String object;

    private Long created;

    private String model;

    private GPTChoices[] choices;

    private GPTUsage usage;

}


