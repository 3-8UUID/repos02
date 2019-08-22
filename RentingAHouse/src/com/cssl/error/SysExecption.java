package com.cssl.error;
//自定义异常类
public class SysExecption extends Exception{
    public String message;

    public SysExecption(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
