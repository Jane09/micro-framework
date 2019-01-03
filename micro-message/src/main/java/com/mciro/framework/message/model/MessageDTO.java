package com.mciro.framework.message.model;

import lombok.Getter;
import lombok.Setter;
/**
 * 100% 消息传输成功
 * batch模式提高TPS
 * @author tb
 * @date 2019/1/3 11:42
 */
@Getter
@Setter
public class MessageDTO {

    private Long msgId;
    //关联上一个msgId 判断是否漏传
    private Long pmsgId;
    private String message;
}
