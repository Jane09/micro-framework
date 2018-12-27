package com.micro.framework.common.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tb
 * @date 2018/12/27 18:00
 */
@ControllerAdvice(value = "com.micro.framework.security")
@ResponseBody
public class GlobalExceptionHandler {
}
