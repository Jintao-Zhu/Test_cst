package net.mooctest;

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

}
