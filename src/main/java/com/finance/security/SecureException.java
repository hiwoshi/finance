package com.finance.security;

/**
 * @author : shenhao
 * @date : 2019/11/15 11:17
 */
public class SecureException extends Exception {

    public SecureException(){

    }

    public SecureException(String message){
        super(message);
    }

    public SecureException(String message, Throwable e){
        super(message,e);
    }

}
