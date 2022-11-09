package com.bartoszwalter.students.bank;

public class BrakRachunku extends Exception {

    public BrakRachunku() {
        super();
    }

    public BrakRachunku(String message) {
        super(message);
    }

    public BrakRachunku(String message, Throwable cause) {
        super(message, cause);
    }

    public BrakRachunku(Throwable cause) {
        super(cause);
    }

    protected BrakRachunku(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
