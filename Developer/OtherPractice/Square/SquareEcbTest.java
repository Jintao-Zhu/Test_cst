package net.mooctest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

public class SquareEcbTest {
	
	@Test
	public void test() {
		SquareEcb secb = new SquareEcb();
		byte[] byte0 = new byte[16];
		
		secb.setKey(byte0);
		secb.setIV(byte0);
		
		secb.decrypt(byte0, 0, 16);
		secb.encrypt(byte0, 0, 16);
		
	}
	
	@Test
	public void test1() throws Throwable {

		SquareEcb scbc = new SquareEcb();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,
				-63,-100,38,-8,-49};

		scbc.encrypt(b2, 0, 16);

		assertArrayEquals(new byte[] {6,9,62,-66,-67,80,-111,-14,58,-73,116,
				-9,57,28,99,-65}, b2);
		
	}
	
	@Test
	public void test2() throws Throwable {
		
		SquareEcb scbc = new SquareEcb();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,
				-63,-100,38,-8,-49};
		
		scbc.decrypt(b2, 0, 16);
		
		assertArrayEquals(new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}, b2);
		
	}

}
