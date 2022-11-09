package com.bartoszwalter.students.bank.odsetki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.rachunki.Rachunek;
import com.bartoszwalter.students.bank.rachunki.StandardowyRachunek;
import junit.framework.TestCase;

import java.util.TreeMap;

public class ProgresywnyMechanizmOdsetkowyTest extends TestCase {

    private Rachunek rachunek;

    protected void setUp() throws Exception {
        super.setUp();
        MechanizmOdsetkowy mechanizmOdsetkowy = progresywnyMechanizmOdstekowy();
        rachunek = new StandardowyRachunek("1", "Jan", "Nowak", mechanizmOdsetkowy);
    }

    private static MechanizmOdsetkowy progresywnyMechanizmOdstekowy() {
        TreeMap<Kwota, Procent> maksymalnaKwotaDoOprocentowania = new TreeMap<>();
        maksymalnaKwotaDoOprocentowania.put(Kwota.z(1000), Procent.z(1));
        maksymalnaKwotaDoOprocentowania.put(Kwota.z(2000), Procent.z(2));
        maksymalnaKwotaDoOprocentowania.put(Kwota.z(3000), Procent.z(3));
        return ProgresywnyMechanizmOdsetkowy.z(maksymalnaKwotaDoOprocentowania);
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

    public void testOdsetkiZ2000SaRowne40() {
        rachunek.wplata(Kwota.z(2000));
        Kwota oczekiwana = Kwota.z(40);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }

    public void testOdsetkiZ3000SaRowne90() {
        rachunek.wplata(Kwota.z(3000));
        Kwota oczekiwana = Kwota.z(90);
        Kwota odsetki = rachunek.odsetki();
        assertEquals(oczekiwana, odsetki);
    }
}