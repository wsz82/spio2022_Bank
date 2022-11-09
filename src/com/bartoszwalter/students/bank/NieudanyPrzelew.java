package com.bartoszwalter.students.bank;

public class NieudanyPrzelew extends Exception {

    public NieudanyPrzelew() {
        super();
    }

    public NieudanyPrzelew(Throwable cause) {
        super(cause);
    }

    public NieudanyPrzelew(String message) {
        super(message);
    }

    public NieudanyPrzelew(String message, Throwable cause) {
        super(message, cause);
    }

    protected NieudanyPrzelew(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
