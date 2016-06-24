package com.kaishengit.exception;

public class DataAccessException extends RuntimeException {

    public DataAccessException() {}

    public DataAccessException(String msg) {
        super(msg);
    }

    public DataAccessException(String msg,Exception ex) {
        super(msg,ex);
    }

}
