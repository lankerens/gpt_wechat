package com.lankerens.gpt.event;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * @description:
 * @author: lankerens
 * @time: 2023/3/25 下午1:58
 */
@ToString
@Accessors(chain = true)
public class BizEvent<T> extends ApplicationEvent implements Serializable {

    private T data;

    public BizEvent(T source) {
        super(source);
        this.data = source;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
