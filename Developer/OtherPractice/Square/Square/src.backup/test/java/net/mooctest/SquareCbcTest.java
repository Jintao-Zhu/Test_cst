package net.mooctest;

import org.junit.Test;

public class SquareCbcTest {

	@Test
	public void test0() throws Throwable {
		
		byte[] byte0 = new byte[16];
		SquareCbc scbc = new SquareCbc();
		scbc.setKey(byte0);
		scbc.setIV(byte0);
		
		scbc.encrypt(byte0, 0, 16);
		scbc.decrypt(byte0, 0, 16);
		
		scbc.finalize();
		
	}
	
	
}
