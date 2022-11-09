package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;

public class LiniowyMechanizmOdsetkowy implements MechanizmOdsetkowy {
    private final Procent oprocentowanie;

    public static LiniowyMechanizmOdsetkowy z(Procent oprocentowanie) {
        return new LiniowyMechanizmOdsetkowy(oprocentowanie);
    }

    private LiniowyMechanizmOdsetkowy(Procent oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    @Override
    public Kwota obliczOdsetki(Kwota saldo) {
        return Util.odsetki(saldo, oprocentowanie);
    }

    @Override
    public String opis() {
        return "Liniowy";
    }
}
