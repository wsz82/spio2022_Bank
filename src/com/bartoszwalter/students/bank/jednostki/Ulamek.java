package com.bartoszwalter.students.bank.jednostki;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Ulamek<A extends Ulamek<A>> extends Porywnywalny<Ulamek<A>> {

    protected Ulamek(BigDecimal opakowany) {
        super(opakowany);
    }

    public A pomnoz(Ulamek<?> ulamek) {
        return this.zUlamka(opakowany.multiply(ulamek.opakowany));
    }

    public A podziel(Ulamek<?> ulamek) {
        return this.zUlamka(opakowany.divide(ulamek.opakowany, RoundingMode.DOWN));
    }

    public A dodaj(A ulamek) {
        return this.zUlamka(opakowany.add(ulamek.opakowany));
    }

    public A odejmij(A ulamek) {
        return this.zUlamka(opakowany.subtract(ulamek.opakowany));
    }

    protected abstract A zUlamka(BigDecimal ulamek);

}
