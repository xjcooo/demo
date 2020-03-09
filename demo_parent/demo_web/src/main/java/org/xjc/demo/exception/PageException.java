package org.xjc.demo.exception;

/**
 * Created by xjc on 2018-12-10.
 */
public class PageException extends Exception {

    public PageException() {
    }

    public PageException(String message) {
        super(message);
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }
}
