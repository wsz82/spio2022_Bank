package com.bartoszwalter.students.bank.rachunki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.odsetki.MechanizmOdsetkowy;

public class DebetowyRachunek extends DekoratorRachunku {
    private final Kwota dopuszczalnyDebet;

    public DebetowyRachunek(Rachunek opakowany, Kwota dopuszczalnyDebet) {
        super(opakowany);
        this.dopuszczalnyDebet = dopuszczalnyDebet;
    }

    @Override
    public String numer() {
        return opakowany.numer();
    }

    @Override
    public String wlasciciel() {
        return opakowany.wlasciciel();
    }

    @Override
    public Kwota saldo() {
        return opakowany.saldo();
    }

    @Override
    public void ustawSaldo(Kwota saldo) {
        opakowany.ustawSaldo(saldo);
    }

    @Override
    public void piszHistorie() {
        opakowany.piszHistorie();
    }

    @Override
    public void wplata(Kwota kwota) {
        ustawSaldo(saldo().dodaj(kwota));
    }

    @Override
    public void wyplata(Kwota kwota) throws NieudanaWyplata {
        Kwota saldo = saldo();
        Kwota saldoZDopuszczalnymDebetem = saldo.dodaj(dopuszczalnyDebet);
        if (kwota.mniejszyRowny(saldoZDopuszczalnymDebetem)) {
            Kwota saldoPomniejszoneOKwote = saldo().odejmij(kwota);
            ustawSaldo(saldoPomniejszoneOKwote);
            dodajHistorie("Wypłata: " + kwota + ", saldo: " + saldo);
        } else {
            dodajHistorie("Nieudana wypłata: " + kwota + ", saldo: " + saldo + "dopuszczalny debet: " + dopuszczalnyDebet);
            throw new NieudanaWyplata();
        }
    }

    @Override
    public Kwota odsetki() {
        return opakowany.odsetki();
    }

    @Override
    public void dodajHistorie(String wpis) {
        opakowany.dodajHistorie(wpis);
    }

    @Override
    public void ustawMechanizmOdsetkowy(MechanizmOdsetkowy mechanizmOdsetkowy) {
        opakowany.ustawMechanizmOdsetkowy(mechanizmOdsetkowy);
    }

    @Override
    public String opisMechanizmuOdsetkowego() {
        return opakowany.opisMechanizmuOdsetkowego();
    }


}
