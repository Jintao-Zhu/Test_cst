package net.mooctest;

import org.junit.Test;

public class SquareCtsTest {

	@Test
	public void test() throws Throwable {
		byte[] byte0 = new byte[16];
		SquareCts scts = new SquareCts();
		
		scts.setKey(byte0);
		scts.setIV(byte0);
		
		scts.decrypt(byte0, 0, 16);
		scts.encrypt(byte0, 0, 16);
		
		scts.finalize();
	}
}
