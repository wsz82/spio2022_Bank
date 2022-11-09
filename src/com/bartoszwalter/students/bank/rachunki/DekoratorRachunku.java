package com.bartoszwalter.students.bank.rachunki;

import java.util.Objects;

public abstract class DekoratorRachunku implements Rachunek {
    protected Rachunek opakowany;

    protected DekoratorRachunku(Rachunek opakowany) {
        this.opakowany = opakowany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DekoratorRachunku that = (DekoratorRachunku) o;
        return Objects.equals(opakowany, that.opakowany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opakowany);
    }
}
