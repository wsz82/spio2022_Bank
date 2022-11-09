package com.bartoszwalter.students.bank.rachunki;

import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.odsetki.MechanizmOdsetkowy;

import java.util.ArrayList;
import java.util.Objects;

public class StandardowyRachunek implements Rachunek {
	private final ArrayList<String> historia = new ArrayList<>();
	private final String numer;
	private final String imie, nazwisko;
	private Kwota saldo;
	private MechanizmOdsetkowy mechanizmOdsetkowy;

	public StandardowyRachunek(String numer, String imie, String nazwisko, MechanizmOdsetkowy mechanizmOdsetkowy) {
		this.numer = numer;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.saldo = Kwota.z(0);
		this.mechanizmOdsetkowy = mechanizmOdsetkowy;
	}

	@Override
	public String numer() {
		return numer;
	}

	@Override
	public String wlasciciel() {
		return imie + " " + nazwisko;
	}

	@Override
	public Kwota saldo() {
		return saldo;
	}

	@Override
	public void ustawSaldo(Kwota saldo) {
		this.saldo = saldo;
	}

	@Override
	public void piszHistorie() {
		System.out.println(historia);
	}

	@Override
	public void wplata(Kwota kwota) {
		saldo = saldo.dodaj(kwota);
		historia.add("Wpłata: " + kwota + ", saldo: " + saldo);
	}

	@Override
	public void wyplata(Kwota kwota) throws NieudanaWyplata {
		if (kwota.mniejszyRowny(saldo)) {
			saldo = saldo.odejmij(kwota);
			dodajHistorie("Wypłata: " + kwota + ", saldo: " + saldo);
		} else {
			dodajHistorie("Nieudana wypłata: " + kwota + ", saldo: " + saldo);
			throw new NieudanaWyplata();
		}
	}

	@Override
	public Kwota odsetki() {
		Kwota odsetki = mechanizmOdsetkowy.obliczOdsetki(saldo);
		historia.add("Naliczono odsetki w kwocie " + odsetki);
		return odsetki;
	}

	@Override
	public void dodajHistorie(String wpis) {
		historia.add(wpis);
	}

	@Override
	public void ustawMechanizmOdsetkowy(MechanizmOdsetkowy mechanizmOdsetkowy) {
		this.mechanizmOdsetkowy = mechanizmOdsetkowy;
	}

	@Override
	public String opisMechanizmuOdsetkowego() {
		return mechanizmOdsetkowy.opis();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StandardowyRachunek that = (StandardowyRachunek) o;
		return Objects.equals(numer, that.numer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numer);
	}
}
