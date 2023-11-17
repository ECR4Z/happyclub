package com.ming.happyclub.common.enums;
import lombok.Getter;

/**
 * @version 1.0
 * @description: 请求响应
 * @date 2023/11/17
 */
@Getter
public enum ResponseEnum {
    SUCCESS(200,"请求成功"),
    ERROR(1000,"请求失败"),
    ERROR_EXCEPTION(1001,"代码异常");
    ;
    private final int code;
    private final String desc;

    ResponseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResponseEnum formCode(int code) {
        for (ResponseEnum responseEnum : ResponseEnum.values()) {
            if (responseEnum.getCode() == code) {
                return responseEnum;
            }
        }
        return ResponseEnum.ERROR;
    }

}
