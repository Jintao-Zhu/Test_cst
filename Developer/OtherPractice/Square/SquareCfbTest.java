package net.mooctest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

public class SquareCfbTest {
	
	@Test
	public void test0() throws Throwable {

		byte[] byte0 = new byte[16];
		SquareCfb scbc = new SquareCfb();
		scbc.setKey(byte0);
		scbc.setIV(byte0);
		
		scbc.encrypt(byte0, 0, 16);
		scbc.decrypt(byte0, 0, 16);
		
		scbc.finalize();
		Field mf = SquareCfb.class.getDeclaredField("mask");
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

		SquareCfb scbc = new SquareCfb();
		Field mf = SquareCfb.class.getDeclaredField("mask");
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

		SquareCfb scbc = new SquareCfb();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.encrypt(b1, 0, 16);
		
		Field mf = SquareCfb.class.getDeclaredField("mask");
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

		SquareCfb scbc = new SquareCfb();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.encrypt(b1, 0, 16);
		
		byte[] b2 = new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,
				-63,-100,38,-8,-49};
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareCfb.class.getDeclaredField("mask");
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

		SquareCfb scbc = new SquareCfb();
		
		byte[] b1 = new byte[16];
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {60,0,66,-113,-118,-69,-64,-72,79,5,124,
				-63,-100,38,-8,-49};

		scbc.decrypt(b2, 0, 16);

		assertArrayEquals(b1, b2);
		
		Field mf = SquareCfb.class.getDeclaredField("mask");
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
		
		SquareCfb scbc = new SquareCfb();
		
		byte[] b1 = new byte[] {32,5,2,8,35,76,9,23,17,9,70,44,65,74,25,4};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		byte[] b2 = new byte[] {-53,-29,-81,-126,-12,90,37,103,15,29,-78,14,-117,108,-128,-25};
		
		scbc.encrypt(b1, 0, 16);
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareCfb.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}


		assertEquals(-253, sum);
	}
	
	
	@Test
	public void test6() throws Throwable {
		
		SquareCfb scbc = new SquareCfb();
		
		byte[] b2 = new byte[] {-53,-29,-81,-126,-12,90,37,103,15,29,-78,14,-117,108,-128,-25};
		byte[] b1 = new byte[] {32,5,2,8,35,76,9,23,17,9,70,44,65,74,25,4};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.decrypt(b2, 0, 16);
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareCfb.class.getDeclaredField("mask");
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
		
		SquareCfb scbc = new SquareCfb();
		
		byte[] b2 = new byte[] {-53,-29,-81,-126,-12,90,37,103,15,29,-78,14,-117,108,-128,-25};
		byte[] b1 = new byte[] {71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		scbc.setKey(b2);
		scbc.setIV(b2);
		
		scbc.decrypt(b2, 0, 10);
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareCfb.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(8, sum);
		
		byte[] b3 = new byte[] {2,4,2};
		scbc.decrypt(b3, 0, 3);
		
		assertArrayEquals(new byte[] {-112,-31,94}, b3);
		
		byte[] b4 = new byte[] {2,4,2,25,6,32};
		
		scbc.decrypt(b4, 0, 6);
		
		assertArrayEquals(new byte[] {65,-23,34,125,-49,-16}, b4);
		
		byte[] b5 = new byte[] {2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25,
				2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		
		scbc.decrypt(b5, 0, 44);
		
		assertArrayEquals(new byte[] {57,64,-16,-22,121,61,-27,87,119,123,81,114,72,28,-64,-54,-57,-100,-31,-50,122,-87,-36,105,54,-80,43,46,-53,117,-69,25,25,-92,-126,-49,-15,-57,56,-6,-97,112,-86,-73,}, b5);
		
		Field mf1 = SquareCfb.class.getDeclaredField("mask");
		mf1.setAccessible(true);
		byte[] mask1 = (byte[]) mf1.get(scbc);
		
		int sum1 = 0;
		for (byte b : mask1) {
			sum1 += b;
		}
		assertEquals(302,sum1);
		
	}
	
	@Test
	public void test8() throws Throwable {
		
		SquareCfb scbc = new SquareCfb();

		byte[] b1 = new byte[] {71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		scbc.setKey(b1);
		scbc.setIV(b1);
		
		scbc.encrypt(b1, 0, 10);
		
		assertArrayEquals(b1, new byte[] {40,115,-1,-2,58,-44,35,-77,103,126,-78,14,-117,108,-128,-25});
		
		Field mf = SquareCfb.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(393, sum);
		
		byte[] b3 = new byte[] {2,4,2};
		scbc.encrypt(b3, 0, 3);
		
		assertArrayEquals(new byte[] {-104,-103,38}, b3);
		
		byte[] b4 = new byte[] {2,4,2,25,6,32};
		scbc.encrypt(b4, 0, 6);

		assertArrayEquals(new byte[] {-21,109,121,-111,51,13}, b4);
		
		byte[] b5 = new byte[] {2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25,
				2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		
		scbc.encrypt(b5, 0, 44);

		
		assertArrayEquals(new byte[] {-46,75,-113,34,-12,53,88,62,41,-33,-67,18,50,-126,-91,28,8,126,79,-16,114,-63,64,-48,-45,118,10,-7,-68,-124,64,122,115,26,119,83,50,113,-106,41,112,76,101,106}, b5);
		
		Field mf1 = SquareCfb.class.getDeclaredField("mask");
		mf1.setAccessible(true);
		byte[] mask1 = (byte[]) mf1.get(scbc);
		
		int sum1 = 0;
		for (byte b : mask1) {
			sum1 += b;
		}
		assertEquals(838,sum1);
	}
	
	@Test
	public void test9() throws Throwable {
		
		SquareCfb scbc = new SquareCfb();
		
		byte[] b1 = new byte[] {71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		scbc.setKey(b1);
		scbc.setIV(b1);
		scbc.encrypt(b1, 0, 10);
		assertArrayEquals(b1, new byte[] {40,115,-1,-2,58,-44,35,-77,103,126,-78,14,-117,108,-128,-25});
		Field mf = SquareCfb.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}
		
		assertEquals(393, sum);
		
		byte[] b3 = new byte[] {2,4,2,54,12,37,87};
		scbc.encrypt(b3, 0, 7);
		
		for (int i = 0; i < b3.length; i++) {
			System.out.print(b3[i]+",");
		}
		
		assertArrayEquals(new byte[] {-104,-103,38,-33,101,94,20}, b3);
	}
	
	
}
