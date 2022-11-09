package com.bartoszwalter.students.bank.jednostki;

import junit.framework.TestCase;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;

public class KwotaTest extends TestCase {

    public void testKwotaUlamkowaZ1000JestRowna1000() {
        Kwota kwota = Kwota.z(1000);
        BigDecimal oczekiwana = new BigDecimal("1000.00");
        assertEquals(oczekiwana, kwota.opakowany());
    }

    public void testKwotaMozeBycUjemna() {
        Kwota kwota = Kwota.z(-1000);
        BigDecimal oczekiwana = new BigDecimal("-1000.00");
        assertEquals(oczekiwana, kwota.opakowany());
    }

    public void testKwotaMaMaks2MiejscaPoPrzecinku() {
        assertThrows(IllegalArgumentException.class, () -> {
            Kwota.z(new BigDecimal("1000.897"));
        });
    }
}