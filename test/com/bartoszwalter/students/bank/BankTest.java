package com.bartoszwalter.students.bank;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.jednostki.Procent;
import com.bartoszwalter.students.bank.odsetki.MechanizmOdsetkowy;
import com.bartoszwalter.students.bank.odsetki.ProgresywnyMechanizmOdsetkowy;
import com.bartoszwalter.students.bank.rachunki.DebetowyRachunek;
import com.bartoszwalter.students.bank.rachunki.Rachunek;
import junit.framework.TestCase;

import java.util.TreeMap;

public class BankTest extends TestCase {

	private final MechanizmOdsetkowy domyslnyMechanizmOdstekowy = progresywnyMechanizmOdstekowy();

	private static MechanizmOdsetkowy progresywnyMechanizmOdstekowy() {
		TreeMap<Kwota, Procent> maksymalnaKwotaDoOprocentowania = new TreeMap<>();
		maksymalnaKwotaDoOprocentowania.put(Kwota.z(1000), Procent.z(1));
		maksymalnaKwotaDoOprocentowania.put(Kwota.z(1000000), Procent.z(2));
		maksymalnaKwotaDoOprocentowania.put(Kwota.z(1000000000), Procent.z(3));
		return ProgresywnyMechanizmOdsetkowy.z(maksymalnaKwotaDoOprocentowania);
	}

	private Bank bank = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		bank = new Bank(domyslnyMechanizmOdstekowy);
	}

	public void testZalozRachunek() {
		String numer = "111";
		bank.zalozRachunek(numer, "Jan", "Kowalski");
		Rachunek rachunek = bank.szukaj(numer);
		assertNotNull(rachunek);
	}

	public void testSzukajRachunku() {
		String numer = "111";
		Rachunek rachunekOczekiwany = bank.zalozRachunek(numer, "Jan", "Kowalski");
		Rachunek rachunekZalozony = bank.szukaj(numer);
		assertEquals(rachunekOczekiwany, rachunekZalozony);
	}

	public void testPieniadzeSiePrzelewajaZNumeruNaNumer() throws NieudanyPrzelew {
		Rachunek rachunekA = bank.zalozRachunek("1", "a", "A");
		rachunekA.wplata(Kwota.z(300));
		Rachunek rachunekB = bank.zalozRachunek("2", "b", "B");
		bank.przelew("1", "2", Kwota.z(300));
		assertEquals(Kwota.z(300), rachunekB.saldo());
	}

	public void testPieniadzeSiePrzelewajaZRachunkuNaRachunek() throws NieudanyPrzelew {
		Rachunek rachunekA = bank.zalozRachunek("1", "a", "A");
		rachunekA.wplata(Kwota.z(300));
		Rachunek rachunekB = bank.zalozRachunek("2", "b", "B");
		bank.przelew(rachunekA, rachunekB, Kwota.z(300));
		assertEquals(Kwota.z(300), rachunekB.saldo());
	}

	public void testZalozDebet() throws BrakRachunku {
		String numer = "1";
		bank.zalozRachunek(numer, "a", "A");
		bank.zalozDebet(numer, Kwota.z(200));
		Rachunek debetowyRachunek = bank.szukaj(numer);
		boolean czyDebetowy = debetowyRachunek instanceof DebetowyRachunek;
		assertTrue(czyDebetowy);
	}
}
