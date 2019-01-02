package com.micro.framework.common.response.auth;

import com.micro.framework.common.constant.ResultCode;
import com.micro.framework.common.response.BaseResponse;

/**
 * @author tb
 * @date 2019/1/2 11:38
 */
public class TokenFobiddenResponse extends BaseResponse {

    public TokenFobiddenResponse(String message) {
        super(ResultCode.FORBIDDEN_TOKEN.getCode(), message);
    }
}
