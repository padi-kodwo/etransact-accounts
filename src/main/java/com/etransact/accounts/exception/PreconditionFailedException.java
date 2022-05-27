package com.etransact.accounts.exception;

public class PreconditionFailedException extends RuntimeException {

    private int code;
    private String message;

    public PreconditionFailedException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}