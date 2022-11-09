package com.bartoszwalter.students.bank.jednostki;

import junit.framework.TestCase;

import java.math.BigDecimal;

public class ProcentTest extends TestCase {

    public void testProcentZLiczbyCalkowitejJestZmienionyNaUlamek() {
        BigDecimal ulamek = Procent.z(5).opakowany();
        BigDecimal oczekiwany = new BigDecimal("0.05");
        assertEquals(oczekiwany, ulamek);
    }

    public void testProcentZUlamkaJestUlamkiem() {
        BigDecimal ulamek = Procent.z(new BigDecimal("0.01")).opakowany();
        BigDecimal oczekiwany = new BigDecimal("0.01");
        assertEquals(oczekiwany, ulamek);
    }
}