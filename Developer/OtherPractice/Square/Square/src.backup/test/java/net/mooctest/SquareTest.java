package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {

	@Test(timeout = 2000)
	public void test() throws Throwable {
		byte[] byte0 = new byte[16];
		byte[] byte1 = new byte[16];
		Square square = new Square(byte0);
		
		square.blockDecrypt(byte0, 0, byte1, 0);
		square.blockEncrypt(byte0, 0, byte1, 0);
		
		
		
		square.finalize();
		
		
	}
	
	@Test
	public void test1() {
		byte[] byte0 = new byte[16];
		byte[] byte1 = new byte[16];
		Square square = new Square(byte0);
		
		square.blockDecrypt(byte0, 0, byte1, 0);
		square.blockEncrypt(byte0, 0, byte1, 0);
		
		assertArrayEquals(byte1, new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,-63,-100,38,-8,-49});
		assertArrayEquals(byte0, new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
		
	}

}
