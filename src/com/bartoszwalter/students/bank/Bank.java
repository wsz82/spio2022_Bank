package com.bartoszwalter.students.bank;

import java.util.HashMap;

public class Bank {
	private HashMap rachunki = new HashMap();

	/**
	 * Zakładanie rachunku. Rachunek zostanie stworzony i zapamiętany przez bank.
	 * @param numer
	 * @param imie
	 * @param nazwisko
	 * @return
	 */
	public Rachunek zalozRachunek(String numer, String imie, String nazwisko) {
		Rachunek rach = new Rachunek(numer, imie, nazwisko);
		rachunki.put(numer, rach);
		return rach;
	}

	/**
	 * Wyszukiwanie rachunku
	 * @param numer
	 * @return obiekt rachunku, jeżeli zostanie znaleziony, i null, jeżeli go nie ma
	 */	
	public Rachunek szukaj(String numer) {
		return (Rachunek) rachunki.get(numer);
	}
	
	/**
	 * Przelew w kwocie kwota z rachunku o numerze numer1 na rachunek o numerze numerw
	 * @param numer1
	 * @param numer2
	 * @param kwota
	 * @return -1, jeżeli przelew się nie powiedzie, 0 - jeżeli przelew się powiedzie
	 */
	public int przelew(String numer1, String numer2, int kwota) {
		Rachunek rach1 = szukaj(numer1);
		Rachunek rach2 = szukaj(numer2);
		
		return przelew(rach1, rach2, kwota);
	}
	
	/**
	 * Przelew w kwocie kwota z rachunku rach1 na rachunek rach2
	 * @param rach1
	 * @param rach2
	 * @param kwota
	 * @return
	 */
	public int przelew(Rachunek rach1, Rachunek rach2, int kwota) {
		if (rach1.wyplata(kwota) > 0) {
			rach2.wplata(kwota);
			return 1;
		}
		
		return 0;
	}
}