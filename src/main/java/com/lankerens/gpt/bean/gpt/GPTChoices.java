package com.lankerens.gpt.bean.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 上午10:27
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GPTChoices implements Serializable {

    private String text;

    private Integer index;

    private Integer logprobs;

    private String finish_reason;


}
