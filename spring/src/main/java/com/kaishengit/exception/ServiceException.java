package com.kaishengit.exception;

/**
 * 业务层的异常
 */
public class ServiceException extends RuntimeException {

    public ServiceException(){}

    public ServiceException(String message) {
        super(message);
    }



}
