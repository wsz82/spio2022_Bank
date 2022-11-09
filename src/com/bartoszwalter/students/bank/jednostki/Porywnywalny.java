package com.bartoszwalter.students.bank.jednostki;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Porywnywalny<A extends Porywnywalny<A>> implements Comparable<Porywnywalny<A>> {

    protected final BigDecimal opakowany;

    public Porywnywalny(BigDecimal opakowany) {
        this.opakowany = opakowany;
    }

    public final boolean mniejszyNiz(A porownywany) {
        return this.compareTo(porownywany) < 0;
    }

    public final boolean mniejszyRowny(A porownywany) {
        return this.compareTo(porownywany) <= 0;
    }

    public final boolean wiekszyOd(A porownywany) {
        return this.compareTo(porownywany) > 0;
    }

    public final boolean wiekszyRowny(A porownywany) {
        return this.compareTo(porownywany) >= 0;
    }

    public final boolean rowny(A porownywany) {
        return this.compareTo(porownywany) == 0;
    }

    @Override
    public int compareTo(Porywnywalny<A> o) {
        return opakowany().compareTo(o.opakowany());
    }

    protected BigDecimal opakowany() {
        return opakowany;
    }

    @Override
    public String toString() {
        return opakowany.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ulamek<?> ulamek = (Ulamek<?>) o;
        return Objects.equals(opakowany, ulamek.opakowany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opakowany);
    }
}
