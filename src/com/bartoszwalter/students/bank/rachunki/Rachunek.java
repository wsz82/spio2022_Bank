package com.bartoszwalter.students.bank.rachunki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.odsetki.MechanizmOdsetkowy;

public interface Rachunek {

    String numer();

    String wlasciciel();

    Kwota saldo();

    void ustawSaldo(Kwota saldo);

    void piszHistorie();

    void wplata(Kwota kwota);

    void wyplata(Kwota kwota) throws NieudanaWyplata;

    Kwota odsetki();

    void dodajHistorie(String wpis);

    //todo ustawianie mechanizmu odesetkowego powinno być dostępne tylko dla banku
    void ustawMechanizmOdsetkowy(MechanizmOdsetkowy mechanizmOdsetkowy);

    String opisMechanizmuOdsetkowego();
}
