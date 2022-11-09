package com.bartoszwalter.students.bank;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.odsetki.LiniowyMechanizmOdsetkowy;
import com.bartoszwalter.students.bank.odsetki.MechanizmOdsetkowy;
import com.bartoszwalter.students.bank.rachunki.DebetowyRachunek;
import com.bartoszwalter.students.bank.rachunki.Rachunek;
import com.bartoszwalter.students.bank.rachunki.StandardowyRachunek;
import com.bartoszwalter.students.bank.rachunki.NieudanaWyplata;

import java.util.HashMap;

public class Bank {
    private final HashMap<String, Rachunek> rachunki = new HashMap<>();
    private final MechanizmOdsetkowy domyslnyMechanizmOdstekowy;

    public Bank() {
        this.domyslnyMechanizmOdstekowy = domyslnyMechanizmOdstekowy();
    }

    private MechanizmOdsetkowy domyslnyMechanizmOdstekowy() {
        return LiniowyMechanizmOdsetkowy.z(Procent.z(1));
    }

    public Bank(MechanizmOdsetkowy domyslnyMechanizmOdstekowy) {
        this.domyslnyMechanizmOdstekowy = domyslnyMechanizmOdstekowy;
    }

    public Rachunek zalozRachunek(String numer, String imie, String nazwisko) {
        Rachunek rach = new StandardowyRachunek(numer, imie, nazwisko, domyslnyMechanizmOdstekowy);
        rachunki.put(numer, rach);
        return rach;
    }

    public void zalozDebet(String numer, Kwota debet) throws BrakRachunku {
        Rachunek rachunek = rachunki.get(numer);
        if (rachunek == null) {
            throw new BrakRachunku("Braku rachunku numer: " + numer);
        }
        Rachunek debetowyRachunek = new DebetowyRachunek(rachunek, debet);
        rachunki.put(numer, debetowyRachunek);
    }

    public Rachunek zalozRachunek(String numer, String imie, String nazwisko, MechanizmOdsetkowy mechanizmOdsetkowy) {
        if (rachunki.containsKey(numer)) {
            throw new IllegalArgumentException("Ten numer rachunku już istnieje: " + numer + " w banku: " + this);
        }
        Rachunek rachunek = new StandardowyRachunek(numer, imie, nazwisko, mechanizmOdsetkowy);
        rachunki.put(numer, rachunek);
        return rachunek;
    }

    public Rachunek szukaj(String numer) {
        return rachunki.get(numer);
    }

    public void przelew(String numerNadawcy, String numerOdbiorcy, Kwota kwota) throws NieudanyPrzelew {
        Rachunek nadawca = szukaj(numerNadawcy);
        Rachunek odbiorca = szukaj(numerOdbiorcy);
        przelew(nadawca, odbiorca, kwota);
    }

    public void przelew(Rachunek nadawca, Rachunek odbiorca, Kwota kwota) throws NieudanyPrzelew {
		try {
			nadawca.wyplata(kwota);
		} catch (NieudanaWyplata e) {
			throw new NieudanyPrzelew(e);
		}
		odbiorca.wplata(kwota);
    }

    public void zamknijRachunek(String numer) {
        rachunki.remove(numer);
    }
}