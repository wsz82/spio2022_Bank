package com.bartoszwalter.students.bank.jednostki;

import java.math.BigDecimal;

public class Procent extends Ulamek<Procent> {

    /**
     * @param procent jako liczba całkowita, gdzie 0 to 0%, a 100 to 100%
     */
    public static Procent z(int procent) {
        return new Procent(procent);
    }

    /**
     * @param ulamek gdzie 1 to 1% a 100 to 100%
     */
    public static Procent z(BigDecimal ulamek) {
        return new Procent(ulamek);
    }

    private Procent(int procent) {
        super(zrobProcentUlamkowy(procent));
    }

    private Procent(BigDecimal ulamek) {
        super(ulamek);
    }

    private static BigDecimal zrobProcentUlamkowy(int procent) {
        return new BigDecimal(procent).movePointLeft(2);
    }

    @Override
    protected Procent zUlamka(BigDecimal ulamek) {
        return Procent.z(ulamek);
    }
}
