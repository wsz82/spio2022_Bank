package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.rachunki.Rachunek;
import com.bartoszwalter.students.bank.rachunki.StandardowyRachunek;
import junit.framework.TestCase;

public class RegresywnyMechanizmOdsetkowyTest extends TestCase {

    private Rachunek rachunek;

    protected void setUp() throws Exception {
        super.setUp();
        MechanizmOdsetkowy mechanizmOdsetkowy =
                RegresywnyMechanizmOdsetkowy.z(Kwota.z(1000), Procent.z(1), Kwota.z(15));
        rachunek = new StandardowyRachunek("1", "Jan", "Nowak", mechanizmOdsetkowy);
    }

    public void testOdsetkiZ100SaRowne1() {
        rachunek.wplata(Kwota.z(100));
        Kwota oczekiwana = Kwota.z(1);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }

    public void testOdsetkiZ900SaRowne9() {
        rachunek.wplata(Kwota.z(900));
        Kwota oczekiwana = Kwota.z(9);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }

    public void testOdsetkiZ1000SaRowne10() {
        rachunek.wplata(Kwota.z(1000));
        Kwota oczekiwana = Kwota.z(10);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }

    public void testOdsetkiZ2000SaRowne15() {
        rachunek.wplata(Kwota.z(2000));
        Kwota oczekiwana = Kwota.z(15);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }
}