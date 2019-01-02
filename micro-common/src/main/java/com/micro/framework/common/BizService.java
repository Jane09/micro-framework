package com.micro.framework.common;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BizService<T> {
    String UNKNOWN = "unknown";
    String X_FORWARDED_FOR = "x-forwarded-for";
    String PROXY_CLIENT_IP = "Proxy-Client-IP";
    String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    String HEADER_USERHOST = "userHost";
    String HEADER_USERNAME = "userName";
    String HEADER_USERID = "userId";
    String[] CREATE_FIELDS = {"createBy","createAt","createIp"};
    String[] UPDATE_FIELDS = {"updateBy","updateAt","updateIp"};

    T selectOne(T entity);
    T findById(Object id);
    List<T> findByEntity(T entity);
    List<T> findAll();
    Long count(T entity);
    void insert(T entity);
    void insertSelective(T entity);
    void update(T entity);
    void updateSelective(T entity);
    void delete(T entity);
    void deleteById(Object id);
    List<T> selectByExample(Object example);
    int countByExample(Object example);


    default void fill(T entity) {
        fillCreate(entity);
        fillUpdate(entity);
    }

    default void fillCreate(T entity) {
        HttpServletRequest request = getRequest();
        if(request != null){
            String ip = getIP(request);
        }
    }

    default void fillUpdate(T entity) {

    }

    default void setDefValues(T entity, String[] fileds, Object[] value) {
        for(String field: fileds) {
        }
    }

    default HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if(attributes instanceof ServletRequestAttributes) {
            request = ((ServletRequestAttributes)attributes).getRequest();
        }
        return request;
    }

    default String getIP(HttpServletRequest request){
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (ip==null||ip.length()==0||UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (ip==null||ip.length()==0||UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ip==null||ip.length()==0|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
