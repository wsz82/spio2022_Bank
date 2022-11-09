package com.bartoszwalter.students.bank.jednostki;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Kwota extends Ulamek<Kwota> {

    public static final int DOPUSZCZALNA_SKALA = 2;
    public static final RoundingMode SPOSOB_ZAOOKRAGLENIA = RoundingMode.DOWN;

    public static Kwota z(int liczbaCalkowita) {
        return new Kwota(liczbaCalkowita);
    }

    public static Kwota z(BigDecimal liczbaUlamkowa) {
        return new Kwota(kwotaZeSkala2(liczbaUlamkowa));
    }

    private Kwota(int liczbaCalkowita) {
        super(kwotaZeSkala2(new BigDecimal(liczbaCalkowita)));
    }

    private static BigDecimal kwotaZeSkala2(BigDecimal liczbaUlamkowa) {
        if (liczbaUlamkowa.scale() > 2) {
            throw new IllegalArgumentException("Kwota ma więcej od 2 miejsc po przecinku: " + liczbaUlamkowa);
        }
        return liczbaUlamkowa.setScale(DOPUSZCZALNA_SKALA, RoundingMode.UNNECESSARY);
    }

    private Kwota(BigDecimal kwota) {
        super(kwota);
        if (kwota.scale() > DOPUSZCZALNA_SKALA) {
            throw new IllegalArgumentException("Niedopuszczalna skala kwoty: " + kwota);
        }
    }

    @Override
    protected Kwota zUlamka(BigDecimal ulamek) {
        return Kwota.z(zaookraglij(ulamek));
    }

    protected BigDecimal zaookraglij(BigDecimal ulamek) {
        return ulamek.setScale(DOPUSZCZALNA_SKALA, SPOSOB_ZAOOKRAGLENIA);
    }
}
