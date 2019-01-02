package com.micro.framework.api.vo.authority;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/2 11:20
 */
@Getter
@Setter
public class PermissionVo implements Serializable {
    private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
    private String menu;
}
