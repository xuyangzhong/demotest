package com.zxy.Enum;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 15:55 2018/9/20
 */
public enum ErrorCode {
    UNKNOWN(0),
    SUCCESS(1),
    FAIL(2);

    private int code;
    private ErrorCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
