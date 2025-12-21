package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

public class BinaryHeapTest {

//	private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	private static PrintStream originalOut;
//
//	@BeforeClass
//	public static void setUp() {
//		originalOut = System.out;
//		System.setOut(new PrintStream(outputStream));
//	}
//
//	@AfterClass
//	public static void tearDownAndVerify() {
//		System.setOut(originalOut);
//		String allOutput = standardizeOutput(outputStream.toString());
//		assertEquals(645, allOutput.length(), 10);
//	}
//
//	private static String standardizeOutput(String output) {
//		return output
//				.replaceAll("\n", "")
//				.replaceAll("\\s", "")
//				.replaceAll("\\p{C}", "")
//				.replaceAll("[^a-zA-Z0-9]", "");
//	}

//	@Test
//	public void test() throws Overflow{
//		BinaryHeap binaryHeap = new BinaryHeap(3);
//		System.out.println(binaryHeap.findMin());
//		System.out.println(binaryHeap.wellFormed());
//		System.out.println(binaryHeap.deleteMin());
//
//		binaryHeap.insert(5);
//		System.out.println(binaryHeap.findMin());
//		System.out.println(binaryHeap.wellFormed());
//		System.out.println(binaryHeap.deleteMin());
//
//		binaryHeap.insert(2);
//		System.out.println(binaryHeap.wellFormed());
//
//		binaryHeap.insert(-1);
//		System.out.println(binaryHeap.wellFormed());
//
//		binaryHeap.insert(3);
//		System.out.println(binaryHeap.wellFormed());
//
//		try {
//			binaryHeap.insert(4);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(binaryHeap.wellFormed());
//
//		binaryHeap.buildHeap();
//		binaryHeap.makeEmpty();
//
//	}
//
//	@Test
//	public void testBinaryHeap() throws NoSuchFieldException, IllegalAccessException {
//		BinaryHeap binaryHeap2 = new BinaryHeap();
//		Field arrayf = BinaryHeap.class.getDeclaredField("array");
//		arrayf.setAccessible(true);
//		arrayf.set(binaryHeap2, null);
//		System.out.println(binaryHeap2.wellFormed());
//
//		arrayf.set(binaryHeap2, new int[]{0, 2, 1, 3});
//		System.out.println(binaryHeap2.wellFormed());
//		arrayf.set(binaryHeap2, new int[]{0, 2, 3, 1});
//		System.out.println(binaryHeap2.wellFormed());
//
//		Field cursizef = BinaryHeap.class.getDeclaredField("currentSize");
//		cursizef.setAccessible(true);
//		cursizef.set(binaryHeap2, -1);
//		System.out.println(binaryHeap2.wellFormed());
//
//		cursizef.set(binaryHeap2, 100);
//		System.out.println(binaryHeap2.wellFormed());
//		arrayf.set(binaryHeap2, new int[]{0, 2, 1, 3});
//		System.out.println(binaryHeap2.wellFormed());
//
//		cursizef.set(binaryHeap2, 3);
//		System.out.println(binaryHeap2.wellFormed());
//		arrayf.set(binaryHeap2, new int[]{0, 2, 3, 1});
//		System.out.println(binaryHeap2.wellFormed());
//	}
//
//	@Test
//	public void testPercolateDown() throws Overflow, NoSuchFieldException, IllegalAccessException {
//		BinaryHeap binaryHeap = new BinaryHeap();
//		binaryHeap.insert(2);
//		binaryHeap.insert(3);
//		binaryHeap.insert(1);
//		Field cursizef = BinaryHeap.class.getDeclaredField("currentSize");
//		cursizef.setAccessible(true);
//		cursizef.set(binaryHeap, 10);
//		System.out.println(binaryHeap.wellFormed());
//		System.out.println(binaryHeap.deleteMin());
//		assertEquals(false, binaryHeap.wellFormed());
//		assertEquals(0, binaryHeap.deleteMin());
//	}
//
//	@Test
//	public void testInsert() throws Overflow {
//		BinaryHeap binaryHeap = new BinaryHeap();
//		binaryHeap.insert(2);
//		binaryHeap.insert(1);
//		binaryHeap.insert(3);
//		System.out.println(binaryHeap.deleteMin());
//	}
//
//	@Test
//	public void testBuildHeap() throws Overflow, NoSuchFieldException, IllegalAccessException {
//		BinaryHeap binaryHeap = new BinaryHeap(10);
//		Field field = BinaryHeap.class.getDeclaredField("array");
//		field.setAccessible(true);
//		field.set(binaryHeap, new int[]{0, 10, 20, 15, 30, 25});
//		Field field1 = BinaryHeap.class.getDeclaredField("currentSize");
//		field1.setAccessible(true);
//		field1.set(binaryHeap, 5);
//		binaryHeap.buildHeap();
//		System.out.println(binaryHeap.wellFormed());
//
//
//
//	}

	@Test
	public void test111() throws NoSuchFieldException, IllegalAccessException {
		final int ARRAY_LENGTH = 1000;
		Random random = new Random(42); // 固定种子确保可复现
		String a = "";
		for (int currentSize = -1000; currentSize <= 2000; currentSize++) {
			BinaryHeap heap = new BinaryHeap(10); // 实际 array 我们会手动改掉

			// 构造长度为1000的array，元素是随机int
			int[] array = new int[ARRAY_LENGTH];
			for (int i = 0; i < ARRAY_LENGTH; i++) {
				array[i] = random.nextInt(10000) - 5000; // 范围[-5000, 4999]
			}

			// 反射设置 array
			Field arrayField = BinaryHeap.class.getDeclaredField("array");
			arrayField.setAccessible(true);
			arrayField.set(heap, array);

			// 设置 currentSize
			Field sizeField = BinaryHeap.class.getDeclaredField("currentSize");
			sizeField.setAccessible(true);
			sizeField.setInt(heap, currentSize);

			// 执行 wellFormed() 并输出可疑行为
			boolean result = heap.wellFormed();
			if (result) a += "1";
			else a += "0";
		}
//		System.out.println(a);
		assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", a);
	}

}
