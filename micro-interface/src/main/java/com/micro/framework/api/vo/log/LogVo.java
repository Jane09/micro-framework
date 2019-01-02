package com.micro.framework.api.vo.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tb
 * @date 2019/1/2 11:21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogVo implements Serializable {
    private String menu;
    private String opt;
    private String uri;
    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
}
