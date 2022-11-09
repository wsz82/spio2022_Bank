package com.bartoszwalter.students.bank.rachunki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.odsetki.LiniowyMechanizmOdsetkowy;
import junit.framework.TestCase;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;

public class StandardowyRachunekTest extends TestCase {

    private Rachunek rachunek;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        LiniowyMechanizmOdsetkowy mechanizmOdsetkowy = LiniowyMechanizmOdsetkowy.z(Procent.z(1));
        rachunek = new StandardowyRachunek("1", "Jan", "Nowak", mechanizmOdsetkowy);
    }

    public void testWplata1000() {
        Kwota kwota = Kwota.z(1000);
        rachunek.wplata(kwota);
        assertEquals(kwota, rachunek.saldo());
    }

    public void testWplata1000wplata500() {
        rachunek.wplata(Kwota.z(1000));
        rachunek.wplata(Kwota.z(500));
        assertEquals(Kwota.z(1500), rachunek.saldo());
    }

    public void testWplata900() {
        Kwota kwota = Kwota.z(900);
        rachunek.wplata(kwota);
        assertEquals(kwota, rachunek.saldo());
    }


    public void testWplata1() {
        Kwota kwota = Kwota.z(1);
        rachunek.wplata(kwota);
        assertEquals(kwota, rachunek.saldo());
    }


    public void testWplata55_23() {
        Kwota kwota = Kwota.z(new BigDecimal("55.23"));
        rachunek.wplata(kwota);
        assertEquals(kwota, rachunek.saldo());
    }

    public void testWyplata300z1000() throws Exception {
        rachunek.wplata(Kwota.z(1000));
        rachunek.wyplata(Kwota.z(300));
        assertEquals(Kwota.z(700), rachunek.saldo());
    }

    public void testWyplata500z200jestNiemozliwa() {
        rachunek.wplata(Kwota.z(200));
        assertThrows(NieudanaWyplata.class, () -> {
            rachunek.wyplata(Kwota.z(500));
        });
    }

    public void testWyplata2z1jestNiemozliwa() {
        rachunek.wplata(Kwota.z(1));
        assertThrows(NieudanaWyplata.class, () -> {
            rachunek.wyplata(Kwota.z(2));
        });
    }
}