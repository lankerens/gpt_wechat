package com.lankerens.gpt.bean.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 上午10:35
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GPTUsage implements Serializable {

    private Integer prompt_tokens;

    private Integer completion_tokens;

    private Integer total_tokens;

}
