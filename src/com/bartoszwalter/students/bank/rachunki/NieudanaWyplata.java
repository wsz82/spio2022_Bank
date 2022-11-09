package com.bartoszwalter.students.bank.rachunki;

public class NieudanaWyplata extends Exception {

    public NieudanaWyplata() {
        super();
    }

    public NieudanaWyplata(String message) {
        super(message);
    }

    public NieudanaWyplata(String message, Throwable cause) {
        super(message, cause);
    }

    public NieudanaWyplata(Throwable cause) {
        super(cause);
    }

    protected NieudanaWyplata(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
