package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;

class Util {

    static Kwota odsetki(Kwota kwota, Procent procent) {
        return kwota.pomnoz(procent);
    }

}
