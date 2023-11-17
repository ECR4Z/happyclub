package com.ming.happyclub.common.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @description: 消息体
 * @date 2023/11/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message<T>{

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Head{
        @ApiModelProperty(value = "响应编码, 200表示成功返回，其他异常返回", required = true, example = "200")
        private Integer respCode;

        @ApiModelProperty(value = "返回消息", required = true, example = "成功")
        private String respMsg;
    }


    @ApiModelProperty(value = "响应头", required = true)
    private Head head;


    @ApiModelProperty(value = "响应体", required = true)
    private T body;


    /**
     * @description: Message封装体
     * @date: 2023/11/17
     */
    public static <T> Message out(Integer code, String msg, T t) {
        Head head = Head.builder()
                .respCode(code)
                .respMsg(msg)
                .build();

        return Message.builder()
                .head(head)
                .body(t)
                .build();
    }

}
