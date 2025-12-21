package net.mooctest;

import org.junit.Test;

public class SquareOfbTest {

	@Test
	public void test() throws Throwable {
		byte[] byte0 = new byte[16];
		SquareOfb sofb = new SquareOfb();
		
		sofb.setKey(byte0);
		sofb.setIV(byte0);
		
		sofb.decrypt(byte0, 0, 16);
		sofb.encrypt(byte0, 0, 16);
		
		sofb.finalize();
	}
	
	
}
