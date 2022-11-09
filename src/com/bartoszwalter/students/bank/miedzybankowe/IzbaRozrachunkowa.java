package com.bartoszwalter.students.bank.miedzybankowe;

import com.bartoszwalter.students.bank.Bank;
import com.bartoszwalter.students.bank.jednostki.Kwota;
import com.bartoszwalter.students.bank.rachunki.Rachunek;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IzbaRozrachunkowa {
    private final Map<String, Bank> rejestrBankow;
    private final List<String> historiaPrzelewow;

    public IzbaRozrachunkowa(Map<String, Bank> rejestrBankow) {
        this.rejestrBankow = rejestrBankow;
        this.historiaPrzelewow = new ArrayList<>();
    }

    public void przyjmijZlecenie(ZleceniePrzelewu zleceniePrzelewu) throws BrakNadawcyZleceniaPrzelewu {
        DanePrzelewu danePrzelewuOdbiorcy = zleceniePrzelewu.danePrzelewuOdbiorcy();
        Rachunek rachunekOdbiorcy = rachunek(danePrzelewuOdbiorcy);
        Kwota kwota = zleceniePrzelewu.kwota();
        if (rachunekOdbiorcy == null) {
            zwrocPrzelewNadawcy(zleceniePrzelewu, kwota);
        } else {
            rachunekOdbiorcy.wplata(kwota);
            dodajHistoriePrzelewu(zleceniePrzelewu, "Udany przelew do odbiorcy");
        }
    }

    private void zwrocPrzelewNadawcy(ZleceniePrzelewu zleceniePrzelewu, Kwota kwota) throws BrakNadawcyZleceniaPrzelewu {
        DanePrzelewu danePrzelewuNadawcy = zleceniePrzelewu.danePrzelewuNadawcy();
        Rachunek rachunekNadawcy = rachunek(danePrzelewuNadawcy);
        if (rachunekNadawcy == null) {
            dodajHistoriePrzelewu(zleceniePrzelewu, "Nieudany zwrot do nadawcy");
            throw new BrakNadawcyZleceniaPrzelewu("Brak nadawcy zlecenia przelewu przy próbie zwrotu przelewu");
        }
        rachunekNadawcy.wplata(kwota);
        dodajHistoriePrzelewu(zleceniePrzelewu, "Udany zwrot do nadawcy");
    }

    private Rachunek rachunek(DanePrzelewu danePrzelewu) {
        String kodBanku = danePrzelewu.kodBanku();
        Bank bank = rejestrBankow.get(kodBanku);
        String numer = danePrzelewu.numerRachunku();
        return bank.szukaj(numer);
    }

    private void dodajHistoriePrzelewu(ZleceniePrzelewu zleceniePrzelewu, String stanPrzelewu) {
        historiaPrzelewow.add("Przelew: " + zleceniePrzelewu.toString() + "\n"  + "Stan przelewu: " + stanPrzelewu);
    }

    public void wypiszHistoriePrzelewow() {
        for (String wpis : historiaPrzelewow) {
            System.out.println(wpis);
        }
    }
}
