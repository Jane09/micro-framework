package com.micro.framework.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/2 11:35
 */
@Getter
@Setter
public class ListResponse<T> {

    private String msg;
    private T result;
    private int count;

    public ListResponse count(int count) {
        this.setCount(count);
        return this;
    }

    public ListResponse count(Long count) {
        this.setCount(count.intValue());
        return this;
    }

    public ListResponse msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ListResponse result(T result) {
        this.setResult(result);
        return this;
    }
}
