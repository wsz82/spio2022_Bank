package com.bartoszwalter.students.bank.miedzybankowe;

public class BrakNadawcyZleceniaPrzelewu extends IllegalStateException {

    public BrakNadawcyZleceniaPrzelewu() {
        super();
    }

    public BrakNadawcyZleceniaPrzelewu(String s) {
        super(s);
    }

    public BrakNadawcyZleceniaPrzelewu(String message, Throwable cause) {
        super(message, cause);
    }

    public BrakNadawcyZleceniaPrzelewu(Throwable cause) {
        super(cause);
    }
}
