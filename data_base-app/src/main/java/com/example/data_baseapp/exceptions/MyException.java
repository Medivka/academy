package com.example.data_baseapp.exceptions;

/**
 * @author Sacuta V.A.
 */


public class MyException extends RuntimeException{
    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

}
