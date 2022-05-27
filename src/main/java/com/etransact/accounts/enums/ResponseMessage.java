package com.etransact.accounts.enums;

import org.springframework.http.HttpStatus;

public enum ResponseMessage {

    SUCCESS(0,"Success"),
    FAILED(-1,"Failed"),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND.value(),""),
    INVALID_KYC(513,"Invalid Kyc"),
    SERVER_ERROR(500,""),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST.value(),"Missing required parameter"),;

    private final int code;
    private final String message;

    ResponseMessage(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseMessage getByCode(final int code) {
        ResponseMessage result = null;
        for (ResponseMessage roleE : values()) {
            if (roleE.getCode() == code) {
                result = roleE;
                break;
            }
        }
        return result;
    }
}
