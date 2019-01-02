package com.micro.framework.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tb
 * @date 2018/12/27 18:04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private int status = 200;
    private String message;
}
