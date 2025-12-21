package net.mooctest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

public class SquareCtsTest {

	@Test
	public void test0() throws Throwable {

		byte[] byte0 = new byte[16];
		SquareCts scbc = new SquareCts();
		scbc.setKey(byte0);
		scbc.setIV(byte0);
		
		scbc.encrypt(byte0, 0, 16);
		scbc.decrypt(byte0, 0, 16);
		
		scbc.finalize();
		Field mf = SquareCts.class.getDeclaredField("mask");
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

		SquareCts scbc = new SquareCts();
		Field mf = SquareCts.class.getDeclaredField("mask");
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

		SquareCts scbc = new SquareCts();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.encrypt(b1, 0, 16);
		
		Field mf = SquareCts.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(110, sum);
		scbc.setKey(b1);
		
		for (int i = 0; i < mask.length; i++) {
			assertEquals(0, mask[i]);
		}
		
	}
	
	@Test
	public void test3() throws Throwable {

		SquareCts scbc = new SquareCts();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.encrypt(b1, 0, 16);
		
		assertArrayEquals(b1, new byte[] {6,9,62,-66,-67,80,-111,-14,58,-73,116,-9,57,28,99,-65,});
		
		Field mf = SquareCts.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(110, sum);
	}
	
	@Test
	public void test4() throws Throwable {

		SquareCts scbc = new SquareCts();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,
				-63,-100,38,-8,-49};

		scbc.decrypt(b2, 0, 16);

		
		assertArrayEquals(new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,-63,-100,38,-8,-49}, b2);
		
		Field mf = SquareCts.class.getDeclaredField("mask");
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
		
		SquareCts scbc = new SquareCts();
		
		byte[] b1 = new byte[] {32,5,2,8,35,76,9,23,17,9,70,44,65,74,25,4};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {-53,-29,-81,-126,-12,90,37,103,15,29,-78,14,-117,108,-128,-25};
		
		scbc.encrypt(b1, 0, 16);

		assertArrayEquals(b1, new byte[] {59,-75,5,84,-29,17,75,23,-81,-100,24,55,-96,58,36,58});
		
		Field mf = SquareCts.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(113, sum);
	}
	
	
	@Test
	public void test6() throws Throwable {
		
		SquareCts scbc = new SquareCts();
		
		byte[] b2 = new byte[] {-53,-29,-81,-126,-12,90,37,103,15,29,-78,14,-117,108,-128,-25};
		byte[] b1 = new byte[] {32,5,2,8,35,76,9,23,17,9,70,44,65,74,25,4};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.decrypt(b2, 0, 16);
		
		assertArrayEquals(new byte[] {-74,-118,48,57,-107,86,88,51,-16,-123,-47,29,52,7,39,-62}, b2);
		
		Field mf = SquareCts.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(-253, sum);
		
	}
	
	@Test
	public void test7() throws Throwable {
		
		SquareCts scbc = new SquareCts();

		byte[] b1 = new byte[] {71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b5 = new byte[] {2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25,
				2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		
		scbc.decrypt(b5, 0, 44);

		assertArrayEquals(new byte[] {109,-72,-50,31,-65,-1,79,110,71,-77,2,87,-9,35,108,-56,117,-12,-71,-45,81,117,-46,9,43,104,-73,-59,
				83,-99,115,-62,89,-65,-9,-50,41,70,-82,-90,98,-112,127,-114}, b5);
		
		
		Field mf1 = SquareCts.class.getDeclaredField("mask");
		mf1.setAccessible(true);
		byte[] mask1 = (byte[]) mf1.get(scbc);
		int sum1 = 0;
		for (byte b : mask1) {
			sum1 += b;
		}

		assertEquals(70,sum1);
	}
	
	@Test
	public void test8() throws Throwable {
		
		SquareCts scbc = new SquareCts();

		byte[] b1 = new byte[] {71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b5 = new byte[] {2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25,
				2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		
		scbc.encrypt(b5, 0, 44);

		assertArrayEquals(new byte[] {-12,-43,-110,101,-121,-124,-67,-121,-105,-68,62,99,34,12,17,38,110,-62,-66,20,-6,9,-115,127,37,-86,
				-125,-90,-94,-118,-106,10,-17,-101,59,-60,-78,-107,8,-2,-37,-55,67,0}, b5);
		
		
		Field mf1 = SquareCts.class.getDeclaredField("mask");
		mf1.setAccessible(true);
		byte[] mask1 = (byte[]) mf1.get(scbc);
		int sum1 = 0;
		for (byte b : mask1) {
			sum1 += b;
		}

		assertEquals(-555,sum1);
		
		try {
			scbc.decrypt(b5, 0, 10);
		} catch (Exception e) {}
		
		try {
			scbc.encrypt(b5, 0, 10);
		} catch (Exception e) {}
	}
}
