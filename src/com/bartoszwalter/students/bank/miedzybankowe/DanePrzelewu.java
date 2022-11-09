package com.bartoszwalter.students.bank.miedzybankowe;

public class DanePrzelewu {
    private final String kodBanku;
    private final String numerRachunku;

    public DanePrzelewu(String kodBanku, String numerRachunku) {
        this.kodBanku = kodBanku;
        this.numerRachunku = numerRachunku;
    }

    public String kodBanku() {
        return kodBanku;
    }

    public String numerRachunku() {
        return numerRachunku;
    }

    @Override
    public String toString() {
        return "DanePrzelewu{" +
                "kodBanku='" + kodBanku + '\'' +
                ", numerRachunku='" + numerRachunku + '\'' +
                '}';
    }
}
