package net.mooctest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class SquareTest {

	@Test(timeout = 2000)
	public void test() throws Throwable {
		byte[] byte0 = new byte[16];
		byte[] byte1 = new byte[16];
		Square square = new Square(byte0);
		
		int a = 0, b = 1;
		
		Method mul = Square.class.getDeclaredMethod("mul", int.class, int.class);
		
		mul.setAccessible(true);
		
		int res = (int) mul.invoke(square, a, b);
		assertEquals(res, 0);
		
		a = 1;
		b = 0;
		res = (int) mul.invoke(square, a, b);
		assertEquals(res, 0);		
		
		square.blockDecrypt(byte0, 0, byte1, 0);
		square.blockEncrypt(byte0, 0, byte1, 0);
		
		square.finalize();
		
		Field f1 = Square.class.getDeclaredField("roundKeys_e");
		Field f2 = Square.class.getDeclaredField("roundKeys_d");
		f1.setAccessible(true);
		f2.setAccessible(true);
		int[][] re = (int[][]) f1.get(square);
		int[][] rd = (int[][]) f2.get(square);
		
		for (int i = 0; i < re.length; i++) {
			for (int j = 0; j < re[0].length; j++) {
				assertEquals(0, re[i][j]);
				assertEquals(0, rd[i][j]);
			}
		}
		
		
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
