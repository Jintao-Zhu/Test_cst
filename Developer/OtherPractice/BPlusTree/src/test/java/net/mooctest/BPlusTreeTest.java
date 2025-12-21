package net.mooctest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BPlusTreeTest {

	private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private static PrintStream originalOut;

	@BeforeClass
	public static void setUp() {
		originalOut = System.out;
		System.setOut(new PrintStream(outputStream));
	}

	@AfterClass
	public static void tearDownAndVerify() {
		System.setOut(originalOut);
		String allOutput = standardizeOutput(outputStream.toString());
		assertEquals(334, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test
	public void testBPlusTree1(){
		BPlusTree<Integer> tree = new BPlusTree<Integer>(4);
		System.out.println(tree.order(100));
		System.out.println(tree.search(100));
		int[] keys = {3,7,9,23,45,1,5,14,25,24,13,8,11,19,4,31,35,56};
		for (int key : keys) {
			tree.insert(key, key);
		}
		for (int key : keys) {
			System.out.println(tree.order(key-1));
			System.out.println(tree.search(key));
		}
		try {
			tree.insert(3, 3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(tree.inOrder());
		System.out.println(tree.reverseInOrder());
		System.out.println(tree.getMinGap());
		System.out.println(tree.getSize());
		System.out.println(tree.toString());
		System.out.println(tree.order(100));
		System.out.println(tree.search(100));
	}

	@Test
	public void testBPlusTree2(){
		BPlusTree<Integer> tree = new BPlusTree<Integer>(5, 3);
		System.out.println(tree.order(100));
		System.out.println(tree.search(100));
		System.out.println(tree.toString());
		int[] keys = {3,7,9,20,6,45,1,5,14,25,24,13,8,11,19,4,31,35,56};
		for (int key : keys) {
			tree.insert(key, key);
		}
		for (int key : keys) {
			System.out.println(tree.order(key));
			System.out.println(tree.search(key));
		}
		try {
		    tree.insert(3, 3);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}

	@Test
	public void testBPlusTree3(){
		BPlusTree<Integer> tree = new BPlusTree<Integer>(2);
		System.out.println(tree.order(100));
		System.out.println(tree.search(100));
		System.out.println(tree.toString());
		int[] keys = {3,3,7,9,20,6,45,1,5,14,25,24,13,8,11,19,4,31,35,56};
		for (int key : keys) {
			try {
				tree.insert(key, key);
			} catch (Exception e) {
			    System.out.println(e.getMessage());
			}
		}
	}

	@Test
	public void testBPlusTree4(){
		IntegerBloomFilter filter = new IntegerBloomFilter(0.0001, 3);
		System.out.println(filter.getExpectedFalsePositiveProbability());
		System.out.println(filter.getCurrentFalsePositiveProbability());
		System.out.println(filter.getFalsePositiveProbability(3));
		filter.add(3);
		System.out.println(filter.getBitsPerElement());
		System.out.println(filter.getFilterSize());
		System.out.println(filter.getTotalHashFunctions());
		filter.clear();
	}
}
