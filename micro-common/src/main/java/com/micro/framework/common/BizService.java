package com.micro.framework.common;

public interface BizService<T> {

    default void fillInfo(T entity) {

    }
}
