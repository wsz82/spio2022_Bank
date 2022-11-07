package com.bartoszwalter.students.bank;

import junit.framework.TestCase;

public class BankTest extends TestCase {

	Bank bank = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		bank = new Bank();
	}

	public void testZalozRachunek() {
		Rachunek rachunek = bank.zalozRachunek("111", "Jan", "Kowalski");
		assertEquals("111", rachunek.numer());
		
		rachunek = bank.szukaj("111");
		assertNotNull(rachunek);
		assertEquals("111", rachunek.numer());
	}

	public void testSzukaj() {
		fail("Not yet implemented");
	}

	public void testPrzelewStringStringInt() {
		fail("Not yet implemented");
	}

	public void testPrzelewRachunekRachunekInt() {
		fail("Not yet implemented");
	}

}
