package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;

public class RegresywnyMechanizmOdsetkowy implements MechanizmOdsetkowy {
    private final Kwota progKwotyOprocentowania;
    private final Procent oprocentowanie;
    private final Kwota odsetkiPowyzejMaksKwoty;

    public static RegresywnyMechanizmOdsetkowy z(Kwota progKwotyOprocentowania, Procent oprocentowanie, Kwota odsetkiPowyzejMaksKwoty) {
        return new RegresywnyMechanizmOdsetkowy(progKwotyOprocentowania, oprocentowanie, odsetkiPowyzejMaksKwoty);
    }

    private RegresywnyMechanizmOdsetkowy(Kwota progKwotyOprocentowania, Procent oprocentowanie, Kwota odsetkiPowyzejMaksKwoty) {
        this.progKwotyOprocentowania = progKwotyOprocentowania;
        this.oprocentowanie = oprocentowanie;
        this.odsetkiPowyzejMaksKwoty = odsetkiPowyzejMaksKwoty;
    }

    @Override
    public Kwota obliczOdsetki(Kwota saldo) {
        if (saldo.mniejszyRowny(progKwotyOprocentowania)) {
            return Util.odsetki(saldo,oprocentowanie);
        } else {
            return odsetkiPowyzejMaksKwoty;
        }
    }

    @Override
    public String opis() {
        return "Regresywny";
    }
}
