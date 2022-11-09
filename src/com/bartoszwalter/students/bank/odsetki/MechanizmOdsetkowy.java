package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.Opisywalne;
import com.bartoszwalter.students.bank.jednostki.Kwota;

public interface MechanizmOdsetkowy extends Opisywalne {

    Kwota obliczOdsetki(Kwota saldo);

}
