package com.bartoszwalter.students.bank.miedzybankowe;

import com.bartoszwalter.students.bank.Bank;
import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.odsetki.LiniowyMechanizmOdsetkowy;
import com.bartoszwalter.students.bank.odsetki.MechanizmOdsetkowy;
import com.bartoszwalter.students.bank.rachunki.NieudanaWyplata;
import com.bartoszwalter.students.bank.rachunki.Rachunek;
import junit.framework.TestCase;

import java.util.HashMap;

import static org.junit.Assert.assertThrows;

public class IzbaRozrachunkowaTest extends TestCase {

    private Bank bankA;
    private Bank bankB;
    private Rachunek rachNadawcy;
    private Rachunek rachOdbiorcy;
    private IzbaRozrachunkowa izbaRozrachunkowa;

    private IzbaRozrachunkowa zrobIzbeRozrachunkowa(Bank bankA, Bank bankB) {
        HashMap<String, Bank> rejestrBankow = new HashMap<>();
        rejestrBankow.put("A", bankA);
        rejestrBankow.put("B", bankB);
        return new IzbaRozrachunkowa(rejestrBankow);
    }

    protected void setUp() throws Exception {
        super.setUp();
        MechanizmOdsetkowy mechanizmOdsetkowy = LiniowyMechanizmOdsetkowy.z(Procent.z(2));

        bankA = new Bank(mechanizmOdsetkowy);
        rachNadawcy = bankA.zalozRachunek("2", "Jacek", "Nowak");

        bankB = new Bank(mechanizmOdsetkowy);
        rachOdbiorcy = bankB.zalozRachunek("1", "Wojtek", "Iksiński");

        izbaRozrachunkowa = zrobIzbeRozrachunkowa(bankA, bankB);

        rachNadawcy.wplata(Kwota.z(500));
    }

    public void testPrzyjmijZlecenie() throws NieudanaWyplata {
        DanePrzelewu danePrzelewuNadawcy = new DanePrzelewu("A", "2");
        DanePrzelewu danePrzelewuOdbiorcy = new DanePrzelewu("B", "1");
        Kwota kwota = Kwota.z(300);
        rachNadawcy.wyplata(kwota);
        ZleceniePrzelewu zleceniePrzelewu = new ZleceniePrzelewu(danePrzelewuNadawcy, danePrzelewuOdbiorcy, kwota);
        izbaRozrachunkowa.przyjmijZlecenie(zleceniePrzelewu);
        assertEquals(Kwota.z(300), rachOdbiorcy.saldo());
    }

    public void testPrzelewJestZwrocony() throws NieudanaWyplata {
        DanePrzelewu danePrzelewuNadawcy = new DanePrzelewu("A", "2");
        DanePrzelewu danePrzelewuOdbiorcy = new DanePrzelewu("B", "1");
        Kwota kwotaPrzelewu = Kwota.z(300);
        rachNadawcy.wyplata(kwotaPrzelewu);
        ZleceniePrzelewu zleceniePrzelewu = new ZleceniePrzelewu(danePrzelewuNadawcy, danePrzelewuOdbiorcy, kwotaPrzelewu);
        bankB.zamknijRachunek("1");
        izbaRozrachunkowa.przyjmijZlecenie(zleceniePrzelewu);
        rachNadawcy.wyplata(Kwota.z(100));
        assertEquals(Kwota.z(400), rachNadawcy.saldo());
    }

    public void testPrzelewJestNiewykonanyGdyOdbiorcaINadawcaLikwidujaKonta() throws NieudanaWyplata {
        DanePrzelewu danePrzelewuNadawcy = new DanePrzelewu("A", "2");
        DanePrzelewu danePrzelewuOdbiorcy = new DanePrzelewu("B", "1");
        Kwota kwotaPrzelewu = Kwota.z(300);
        rachNadawcy.wyplata(kwotaPrzelewu);
        ZleceniePrzelewu zleceniePrzelewu = new ZleceniePrzelewu(danePrzelewuNadawcy, danePrzelewuOdbiorcy, kwotaPrzelewu);
        bankA.zamknijRachunek("2");
        bankB.zamknijRachunek("1");
        assertThrows(BrakNadawcyZleceniaPrzelewu.class, () -> {
            izbaRozrachunkowa.przyjmijZlecenie(zleceniePrzelewu);
        });
    }
}