package net.mooctest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

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
		Field mf = SquareCbc.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}
		
		assertEquals(0, sum);
		
	}
	
	@Test
	public void test1() throws Throwable {

		SquareCbc scbc = new SquareCbc();
		Field mf = SquareCbc.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		assertEquals(16, mask.length);
		for (byte b : mask) {
			sum += b;
		}
		assertEquals(0, sum);
		
	}
	
	@Test
	public void test2() throws Throwable {

		SquareCbc scbc = new SquareCbc();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.encrypt(b1, 0, 16);
		
		Field mf = SquareCbc.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}
		
		assertEquals(-284, sum);
		
		scbc.setKey(b1);
		
		for (int i = 0; i < mask.length; i++) {
			assertEquals(0, mask[i]);
		}
		
	}
	
	@Test
	public void test3() throws Throwable {

		SquareCbc scbc = new SquareCbc();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.encrypt(b1, 0, 16);
		
		byte[] b2 = new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,
				-63,-100,38,-8,-49};
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareCbc.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}
		
		assertEquals(-284, sum);
	}
	
	@Test
	public void test4() throws Throwable {

		SquareCbc scbc = new SquareCbc();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,
				-63,-100,38,-8,-49};

		scbc.decrypt(b2, 0, 16);

		assertArrayEquals(b1, b2);
		
		Field mf = SquareCbc.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}
		
		assertEquals(-284, sum);
		
	}
	
	@Test
	public void test5() throws Throwable {
		
		SquareCbc scbc = new SquareCbc();
		
		byte[] b1 = new byte[] {32,5,2,8,35,76,9,23,17,9,70,44,65,74,25,4};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {-84,84,-109,111,-12,-7,114,110,-73,123,98,-95,6,-46,125,74};
		
		scbc.encrypt(b1, 0, 16);
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareCbc.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(419, sum);
	}
	
	
	@Test
	public void test6() throws Throwable {
		
		SquareCbc scbc = new SquareCbc();
		
		byte[] b2 = new byte[] {-84,84,-109,111,-12,-7,114,110,-73,123,98,-95,6,-46,125,74};
		byte[] b1 = new byte[] {32,5,2,8,35,76,9,23,17,9,70,44,65,74,25,4};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.decrypt(b2, 0, 16);
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareCbc.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}
		
		assertEquals(419, sum);
		
	}
}
