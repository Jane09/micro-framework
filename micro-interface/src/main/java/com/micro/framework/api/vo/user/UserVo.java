package com.micro.framework.api.vo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tb
 * @date 2019/1/2 11:25
 */
@Getter
@Setter
public class UserVo implements Serializable {

    public String id;
    public String username;
    public String password;
    public String name;
    private String description;
    private Date updTime;
}
