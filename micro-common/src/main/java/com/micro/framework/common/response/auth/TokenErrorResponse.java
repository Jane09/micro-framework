package com.micro.framework.common.response.auth;

import com.micro.framework.common.constant.ResultCode;
import com.micro.framework.common.response.BaseResponse;

/**
 * @author tb
 * @date 2019/1/2 11:36
 */
public class TokenErrorResponse extends BaseResponse {

    public TokenErrorResponse(String message) {
        super(ResultCode.ERROR_TOKEN.getCode(), message);
    }
}
