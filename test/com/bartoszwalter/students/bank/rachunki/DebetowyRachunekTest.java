package com.bartoszwalter.students.bank.rachunki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.odsetki.LiniowyMechanizmOdsetkowy;
import junit.framework.TestCase;

import static org.junit.Assert.assertThrows;

public class DebetowyRachunekTest extends TestCase {

    private Rachunek rachunek;
    private Kwota dopuszczalnyDebet = Kwota.z(200);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        LiniowyMechanizmOdsetkowy mechanizmOdsetkowy = LiniowyMechanizmOdsetkowy.z(Procent.z(1));
        Rachunek opakowany = new StandardowyRachunek("1", "Jan", "Nowak", mechanizmOdsetkowy);
        rachunek = new DebetowyRachunek(opakowany, dopuszczalnyDebet);
    }

    public void testWplata1000wplata500() {
        rachunek.wplata(Kwota.z(1000));
        rachunek.wplata(Kwota.z(500));
        assertEquals(Kwota.z(1500), rachunek.saldo());
    }

    public void testWyplata200przySaldzie100() throws Exception {
        rachunek.wplata(Kwota.z(100));
        rachunek.wyplata(Kwota.z(200));
        assertEquals(Kwota.z(-100), rachunek.saldo());
    }

    public void testWplata300przySaldzieUjemnym100() {
        rachunek.ustawSaldo(Kwota.z(-100));
        rachunek.wplata(Kwota.z(300));
        assertEquals(Kwota.z(200), rachunek.saldo());
    }

    public void testWyplata200przySaldzie0() throws Exception {
        rachunek.wyplata(Kwota.z(200));
        assertEquals(Kwota.z(-200), rachunek.saldo());
    }

    public void testWyplata201przySaldzie0() {
        assertThrows(NieudanaWyplata.class, () -> {
            rachunek.wyplata(Kwota.z(201));
        });
    }
}