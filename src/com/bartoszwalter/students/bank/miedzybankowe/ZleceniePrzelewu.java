package com.bartoszwalter.students.bank.miedzybankowe;

import com.bartoszwalter.students.bank.jednostki.Kwota;

public class ZleceniePrzelewu {
    private final DanePrzelewu danePrzelewuNadawcy;
    private final DanePrzelewu danePrzelewuOdbiorcy;
    private final Kwota kwota;

    public ZleceniePrzelewu(DanePrzelewu danePrzelewuNadawcy, DanePrzelewu danePrzelewuOdbiorcy, Kwota kwota) {
        this.danePrzelewuNadawcy = danePrzelewuNadawcy;
        this.danePrzelewuOdbiorcy = danePrzelewuOdbiorcy;
        this.kwota = kwota;
    }

    public DanePrzelewu danePrzelewuNadawcy() {
        return danePrzelewuNadawcy;
    }

    public DanePrzelewu danePrzelewuOdbiorcy() {
        return danePrzelewuOdbiorcy;
    }

    public Kwota kwota() {
        return kwota;
    }

    @Override
    public String toString() {
        return "ZleceniePrzelewu{" +
                "danePrzelewuNadawcy=" + danePrzelewuNadawcy +
                ", danePrzelewuOdbiorcy=" + danePrzelewuOdbiorcy +
                ", kwota=" + kwota +
                '}';
    }
}
