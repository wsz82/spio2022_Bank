package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.rachunki.Rachunek;
import com.bartoszwalter.students.bank.rachunki.StandardowyRachunek;
import junit.framework.TestCase;

public class LiniowyMechanizmOdsetkowyTest extends TestCase {

    private Rachunek rachunek;

    protected void setUp() throws Exception {
        super.setUp();
        MechanizmOdsetkowy mechanizmOdsetkowy = LiniowyMechanizmOdsetkowy.z(Procent.z(2));
        rachunek = new StandardowyRachunek("1", "Jan", "Nowak", mechanizmOdsetkowy);
    }

    public void testOdsetkiZ1000SaRowne20() {
        rachunek.wplata(Kwota.z(1000));
        Kwota oczekiwana = Kwota.z(20);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }

    public void testOdsetkiZ2000SaRowne40() {
        rachunek.wplata(Kwota.z(2000));
        Kwota oczekiwana = Kwota.z(40);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }
}