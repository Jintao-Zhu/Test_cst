package net.mooctest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
		assertEquals(50, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test
	public void BPlsuTreetest1(){
	    BPlusTree<Integer> bp1 = new BPlusTree<Integer>(5);
	    System.out.println(bp1.toString());
	    bp1.search(0);
	    bp1.insert(0, 2);
	    bp1.search(0);
	    bp1.insert(0, 1);
	    bp1.search(2);
	    bp1.insert(0, 3);
	    bp1.search(1);
	    bp1.insert(1, 0);
	    bp1.search('2');
	    System.out.println(bp1.reverseInOrder());
	    System.out.println(bp1.getSize());
	    System.out.println(bp1.getMinGap());
	    System.out.println(bp1.inOrder());
	    System.out.println(bp1.order(0));
	    System.out.println(bp1.order(5));
	    System.out.println(bp1.toString());
	}
	
	@Test
	public void BPlsuTreetest2(){
		BPlusTree bp1 = new BPlusTree(5, 3);
		bp1.search(0);
	}

}
