/**
 * 
 */
package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

public class NextdayTest {

	@Test
	public void test() {
		Date date01 = new Date(1, 1, 2024);
		date01.printDate();
		assertEquals(date01.toString(), "1/1/2024");
		date01.increment();
		date01.getDay();
		date01.getMonth();
		date01.getYear();
		
		Date date02 = new Date(2, 29, 2020);
		date02.printDate();
		assertEquals(date02.toString(), "2/29/2020");
		date02.increment();
		date02.getDay();
		date02.getMonth();
		date02.getYear();		
		
		Date date03 = new Date(12, 31, 2022);
		date03.printDate();
		assertEquals(date03.toString(), "12/31/2022");
		date03.increment();
		assertEquals(date03.toString(), "1/1/2023");
		date03.getDay();
		date03.getMonth();
		date03.getYear();
		
		date01.equals(date02);
		assertTrue(date01.equals(date01));
		date01.equals(1);
		date01.equals(date03);
		assertFalse(date02.equals(date03));
		
		Nextday nextday01 = new Nextday();
		nextday01.nextDay(date01);
		
		try {
			Month month1 = new Month(2, new Year(0));
		} catch (Exception e) {
		}
		try {
			Month month7 = new Month(-2, new Year(4));
		} catch (Exception e) {
		}

	}

}
