package com.micro.framework.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/2 11:32
 */
@Getter
@Setter
public class ObjectResponse<T> extends BaseResponse {

    T data;
    boolean rel;

    public ObjectResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }
    public ObjectResponse data(T data) {
        this.setData(data);
        return this;
    }

    public boolean isRel() {
        return rel;
    }

}
