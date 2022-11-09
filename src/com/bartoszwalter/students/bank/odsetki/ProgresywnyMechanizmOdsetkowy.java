package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;

import java.util.TreeMap;

public class ProgresywnyMechanizmOdsetkowy implements MechanizmOdsetkowy {
    private final TreeMap<Kwota, Procent> maksymalnaKwotaDoOprocentowania;

    private ProgresywnyMechanizmOdsetkowy(TreeMap<Kwota, Procent> maksymalnaKwotaDoOprocentowania) {
        this.maksymalnaKwotaDoOprocentowania = maksymalnaKwotaDoOprocentowania;
    }

    public static ProgresywnyMechanizmOdsetkowy z(TreeMap<Kwota, Procent> maksymalnaKwotaDoOprocentowania) {
        return new ProgresywnyMechanizmOdsetkowy(maksymalnaKwotaDoOprocentowania);
    }

    @Override
    public Kwota obliczOdsetki(Kwota saldo) {
        for (Kwota maksymalnaKwota : maksymalnaKwotaDoOprocentowania.keySet()) {
            if (saldo.mniejszyRowny(maksymalnaKwota)) {
                Procent oprocentowanie = maksymalnaKwotaDoOprocentowania.get(maksymalnaKwota);
                return Util.odsetki(saldo, oprocentowanie);
            }
        }
        return Kwota.z(0);
    }

    @Override
    public String opis() {
        return "Progresywny";
    }
}
