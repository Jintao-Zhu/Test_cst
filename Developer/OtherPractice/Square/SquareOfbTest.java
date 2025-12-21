package net.mooctest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

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
		Field mf = SquareOfb.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(sofb);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}
		
		assertEquals(0, sum);
		
		sofb.setKey(byte0);
		
		for (int i = 0; i < mask.length; i++) {
			assertEquals(0, mask[i]);
		}
	}
	
	@Test
	public void test1() throws Throwable {
		
		SquareOfb scbc = new SquareOfb();
		
		byte[] b2 = new byte[] {-53,-29,-81,-126,-12,90,37,103,15,29,-78,14,-117,108,-128,-25};
		byte[] b1 = new byte[] {71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		scbc.setKey(b2);
		scbc.setIV(b2);
		
		scbc.decrypt(b2, 0, 10);
		
		assertArrayEquals(b1, b2);
		
		Field mf = SquareOfb.class.getDeclaredField("mask");
		mf.setAccessible(true);
		byte[] mask = (byte[]) mf.get(scbc);
		
		int sum = 0;
		for (byte b : mask) {
			sum += b;
		}

		assertEquals(-188, sum);
		
		byte[] b3 = new byte[] {2,4,2};
		scbc.decrypt(b3, 0, 3);
		
		assertArrayEquals(new byte[] {-112,-31,94}, b3);
		
		byte[] b4 = new byte[] {2,4,2,25,6,32};
		
		scbc.decrypt(b4, 0, 6);
		
		assertArrayEquals(new byte[] {65,-23,34,-75,-74,25}, b4);
		
		byte[] b5 = new byte[] {2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25,
				2,4,2,25,6,32,71,25,92,37,110,125,41,101,111,-49,-78,14,-117,108,-128,-25};
		
		scbc.decrypt(b5, 0, 44);
		assertArrayEquals(new byte[] {31,109,2,5,-37,10,-56,-29,115,-9,12,-41,94,114,8,-118,18,-41,79,-95,64,116,-105,
				-88,4,79,-59,-10,44,50,61,120,-12,-90,6,100,119,-89,-69,-10,124,24,31,-105}, b5);
		
		Field mf1 = SquareOfb.class.getDeclaredField("mask");
		mf1.setAccessible(true);
		byte[] mask1 = (byte[]) mf1.get(scbc);
		
		int sum1 = 0;
		for (byte b : mask1) {
			sum1 += b;
		}

		assertEquals(441,sum1);
		
	}
	
	
}
