package com.bartoszwalter.students.bank;

import java.util.ArrayList;

public class Rachunek {
	private String numer;
	private String imie, nazwisko;
	private int saldo;
	private int dopuszczalnyDebet;
	private ArrayList historia = new ArrayList();
	
	/**
	 * Utworzenie rachunku
	 * @param numer
	 * @param imie
	 * @param nazwisko
	 */
	public Rachunek(String numer, String imie, String nazwisko) {
		this.numer = numer;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.saldo = 0;
	}
	
	/**
	 * Zwraca numer rachunku
	 * @return
	 */
	public String numer() {
		return numer;
	}
	
	/**
	 * Zwraca właściciela rachunu
	 * @return
	 */
	public String wlasciciel() {
		return imie + " " + nazwisko;
	}
	
	/** 
	 * Zwraca saldo rachunku
	 * @return
	 */
	public int saldo() {
		return saldo;
	}
	
	public void ustawDebet(int debet) {
		if (debet > 100)
			dopuszczalnyDebet = debet;
	}
	
	/**
	 * Ustawia dopuszczalny debet na podaną wartość
	 * @param debet
	 */
	public int debet() {
		return dopuszczalnyDebet;
	}

	/**
	 * Zwraca aktualną wartość dopuszczalnego debetu
	 * @return
	 */
	public void piszHistorie() {
		System.out.println(historia);
	}
	
	/**
	 * Wyświetla historię rachunku
	 *
	 */
	public int wplata(int kwota) {
		saldo += kwota;
		historia.add("Wpłata: " + kwota + ", saldo: " + saldo);
		return 0;
	}
	
	/**
	 * Wpłaca podaną kwotę na rachunek
	 * @param kwota
	 * @return 0
	 */
	public int wyplata(int kwota) {
		if (saldo + dopuszczalnyDebet >= kwota) {
			saldo -= kwota;
			historia.add("Wypłata: " + kwota + ", saldo: " + saldo);
			return 0;
		}
		historia.add("Nieudana wypłata: " + kwota + ", saldo: " + saldo);
		return -1;
	}
	
	/**
	 * Zwraca wartość należnych odsetek: do 10000 - 1%, od 10000 do 50000 - 2%, powyżej 50000 - 3%
	 * @return
	 */
	public int odsetki() {
		int odsetki = 0;
		
		if (saldo < 10000)
			odsetki = (int) (0.01 * saldo);
		else if (saldo < 50000)
			odsetki = 100 + (int) (0.02 * (saldo - 10000));
		else 
			odsetki =  100 + 800 + (int) (0.03 * (saldo - 50000));
		
		historia.add("Naliczono odsetki w kwocie " + odsetki);
		
		return odsetki;
	}
}
