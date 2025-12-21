package net.mooctest;

import org.junit.Test;

public class SquareCfbTest {
	
	@Test
	public void test0() throws Throwable {
		SquareCfb scfb = new SquareCfb();
		byte[] byte0 = new byte[16];
		
		scfb.setKey(byte0);
		scfb.setIV(byte0);
		
		scfb.encrypt(byte0, 0, 15);
		scfb.decrypt(byte0, 0, 16);
		
		scfb.finalize();
		
	}
	
	
}
