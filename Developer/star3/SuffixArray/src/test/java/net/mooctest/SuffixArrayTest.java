package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SuffixArrayTest {

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
		assertEquals(58, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test
	public void testCreateSuffixArray() {
		int[] sa = {7, 3, 1, 5, 0, 6, 2, 4};
		int[] sa1 = {5, 3, 1, 5, 0, 6, 2, 4};
		int[] LCP = {0, 1, 1, 0, 2, 0, 1, 0};
		SuffixArray.createSuffixArray("aabaaaab", sa, LCP);
		SuffixArray.createSuffixArray("aabaaaab", sa1, LCP);
		System.out.println(SuffixArray.isPermutation(sa, 8));
		System.out.println(SuffixArray.isPermutation(sa1, 8));
		SuffixArray.printV(sa, "aabaaaab");
		int[] s = {97, 97, 98, 97, 97, 97, 97, 98};
		System.out.println(SuffixArray.isSorted(sa, s, 3));
		System.out.println(SuffixArray.isSorted(sa, LCP, 8));
	}

	@Test
	public void testCreateSuffixArray1() {
		int[] sa = {7, 3, 1, 5, 0, 6, 2, 4};
		int[] sa1 = {1, 3, 1, 5, 0, 4};
		int[] LCP = {0, 1, 1, 0, 2, 0, 1, 0};
		SuffixArray.createSuffixArray("aaaaaa", sa, LCP);
		System.out.println(SuffixArray.isPermutation(sa1, 6));
	}

	@Test
	public void testCreateSuffixArraySlow() {
		int[] sa1 = {7, 3, 1, 5, 0, 6, 2, 4};
		int[] sa2 = {7, 3, 1, 5, 0, 6, 2, 4, 8};
		int[] LCP1 = {0, 1, 1, 0, 2, 0, 1, 0};
		int[] LCP2 = {0, 1, 1, 0, 2, 0, 1};
		SuffixArray.createSuffixArraySlow("aabaaaab", sa1, LCP1);
		try {
			SuffixArray.createSuffixArraySlow("aabaaaab", sa2, LCP1);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			SuffixArray.createSuffixArraySlow("aabaaaab", sa1, LCP2);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}

	@Test
	public void testComputeLCP() {
		System.out.println(SuffixArray.computeLCP("ab", "a"));
		System.out.println(SuffixArray.computeLCP("ab", "aa"));
		System.out.println(SuffixArray.computeLCP("ab", "abb"));
	}

	@Test
	public void testLeq() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method method = SuffixArray.class.getDeclaredMethod("leq", int.class, int.class, int.class, int.class, int.class, int.class);
		method.setAccessible(true);
		System.out.println(method.invoke(null, 2, 2, 2, 1, 1, 1));
		System.out.println(method.invoke(null, 1, 2, 2, 2, 1, 1));
	}
}
