package net.mooctest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.*;

import org.junit.*;

import com.github.javaparser.ast.Node;


public class BSTTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before Class");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Before Method");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After Class");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After Method");
	}

	@Test
	public void testPutGet() {
		BST<Integer, String> bst = new BST<>();
		assertNull(bst.get(1));
		bst.put(3, "c");
		bst.put(2, "b");
		assertEquals(2, bst.size());
		bst.put(1, "a");
		bst.put(4, "e");
		bst.put(4, "d");
		assertEquals(4, bst.size());

		try {
			bst.put(null, "f");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		bst.put(6, null);

		assertEquals(4, bst.size());
		assertEquals("a", bst.get(1));
		try {
			bst.get(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		bst.put(6, null);

		assertTrue(bst.contains(2));
		assertFalse(bst.contains(6));
		try {
			bst.contains(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRemove() {
		BST<Integer, String> bst = new BST<>();
		try {
			bst.deleteMin();
		} catch (NoSuchElementException e) {}
		try {
			bst.deleteMax();
		} catch (NoSuchElementException e) {}
		bst.put(3, "c");
		bst.put(2, "b");
		bst.put(1, "a");
		bst.put(5, "e");
		bst.put(4, "d");
		bst.put(-6, "f");
		bst.deleteMin();
		assertEquals(5, bst.size());
		bst.deleteMax();
		assertEquals(4, bst.size());
		bst.delete(3);
		assertEquals(3, bst.size());

		try {
			bst.delete(null);
		} catch (IllegalArgumentException e) {}
		bst.delete(2);
		assertEquals(2, bst.size());
		bst.delete(5);
		assertEquals(2, bst.size());
//		bst.delete(4);
//		assertEquals(0, bst.size());
//		bst.delete(6);
//		assertEquals(0, bst.size());
	}

	@Test
	public void testMaxMin() {
		BST<Integer, String> bst = new BST<>();
		try {
			bst.max();
		} catch (NoSuchElementException e) {}
		try {
			bst.min();
		} catch (NoSuchElementException e) {}
		bst.put(3, "c");
		bst.put(2, "b");
		bst.put(1, "a");
		bst.put(5, "e");
		bst.put(4, "d");
		assertEquals("5", bst.max().toString());
		assertEquals("1", bst.min().toString());
	}

	@Test
	public void testFloor() {
		BST<Integer, String> bst = new BST<>();
		assertNull(bst.floor2(null));
		try {
			bst.floor(-1);
		} catch (NoSuchElementException e) {}
		try {
			bst.floor(null);
		} catch (IllegalArgumentException e) {}
		bst.put(3, "c");
		assertEquals("3", bst.floor(3).toString());
		assertNull(bst.floor(1));
		bst.put(2, "b");
		assertEquals("2", bst.floor(2).toString());
		bst.put(1, "a");
		assertEquals("1", bst.floor(1).toString());
		bst.put(5, "e");
		assertEquals("5", bst.floor(5).toString());
		bst.put(4, "d");

		assertEquals("4", bst.floor2(4).toString());
		assertEquals("2", bst.floor2(2).toString());
		assertEquals("3", bst.floor2(3).toString());
	}

	@Test
	public void testCeil() {
		BST<Integer, String> bst = new BST<>();
		try {
			bst.ceiling(-1);
		} catch (NoSuchElementException e) {}
		try {
			bst.ceiling(null);
		} catch (IllegalArgumentException e) {}
		bst.put(3, "c");
		assertEquals("3", bst.ceiling(3).toString());
		assertNull(bst.floor(1));
		bst.put(2, "b");
		assertEquals("2", bst.ceiling(2).toString());
		bst.put(1, "a");
		assertEquals("1", bst.ceiling(1).toString());
		bst.put(5, "e");
		assertEquals("5", bst.ceiling(5).toString());
	}

	@Test
	public void testSelect() {
		BST<Integer, String> bst = new BST<>();
		try {
			bst.select(-1);
		} catch (IllegalArgumentException e) {}
		try {
			bst.select(3);
		} catch (IllegalArgumentException e) {}
		bst.put(3, "c");
		assertEquals("3", bst.select(0).toString());
	}

	@Test
	public void testRank() {
		BST<Integer, String> bst = new BST<>();
		try {
			bst.rank(null);
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testKeys() {
		BST<Integer, String> bst = new BST<>();
		try {
			bst.keys(null, null);
		} catch (IllegalArgumentException e) {}
		try {
			bst.keys(0, null);
		} catch (IllegalArgumentException e) {}
		bst.put(3, "c");
		bst.put(2, "b");
		bst.put(1, "a");
		bst.put(5, "e");
		bst.put(4, "d");
		assertEquals("1 2 3 4 5 ", bst.keys().toString());
	}
	@Test
	public void testSize() {
		BST<Integer, String> bst = new BST<>();
		try {
			bst.keys(null, null);
		} catch (IllegalArgumentException e) {}
		try {
			bst.keys(0, null);
		} catch (IllegalArgumentException e) {}
		bst.put(3, "c");
		bst.put(2, "b");
		bst.put(1, "a");
		bst.put(5, "e");
		bst.put(4, "d");
		assertEquals(0, bst.size(5, 2));
		assertEquals(2, bst.size(1, 2));
		assertEquals(4, bst.size(2, 6));
	}

	@Test
	public void testHeight() {
		BST<Integer, String> bst = new BST<>();
		assertEquals(-1, bst.height());
		bst.put(3, "c");
		assertEquals(0, bst.height());
	}

	@Test
	public void testLevelOrder() {
		BST<Integer, String> bst = new BST<>();
		bst.levelOrder();
		bst.put(3, "c");
		bst.put(2, "b");
		bst.put(1, "a");
		bst.put(5, "e");
		bst.put(4, "d");
		assertEquals("3 2 5 1 4 ", bst.levelOrder().toString());
	}

	@Test
	public void testQueue() {
		Queue<Integer> queue = new Queue<>();

		try {
			queue.dequeue();
		} catch (NoSuchElementException e) {}

		try {
			queue.peek();
		} catch (NoSuchElementException e) {}

		assertEquals(0, queue.size());
		queue.enqueue(1);
		assertEquals(1, queue.size());
		assertEquals("1", queue.peek().toString());
		try {
			queue.iterator().next();
		} catch (NoSuchElementException e) {}
		queue.dequeue();
		assertEquals(0, queue.size());
	}

	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Test
	public void testStdOut() {
		System.setOut(new PrintStream(outputStream));

		String a = "a";
		boolean b = true;
		char c = 'c';
		double d = 3.14;
		float f = 3.14f;
		int i = 42;
		long l = 42;
		short s = (short) 42;
		byte t = (byte) 42;
		StdOut.println(a);
		StdOut.println(b);
		StdOut.println(c);
		StdOut.println(d);
		StdOut.println(f);
		StdOut.println(i);
		StdOut.println(l);
		StdOut.println(s);
		StdOut.println(t);
		StdOut.println();
		StdOut.print(a);
		StdOut.print(b);
		StdOut.print(c);
		StdOut.print(d);
		StdOut.print(f);
		StdOut.print(i);
		StdOut.print(l);
		StdOut.print(s);
		StdOut.print(t);
		StdOut.print();
		StdOut.printf("Hello, %s! You have %d new messages.", "Alice", 5);
		StdOut.printf(Locale.FRANCE, "Le prix est %.2f EUR", 123.45);

		String output = outputStream.toString().trim();
		System.setOut(originalOut);
		String string = "a\r\n" +
				"true\r\n" +
				"c\r\n" +
				"3.14\r\n" +
				"3.14\r\n" +
				"42\r\n" +
				"42\r\n" +
				"42\r\n" +
				"42\r\n" +
				"\r\n" +
				"atruec3.143.1442424242Hello, Alice! You have 5 new messages.Le prix est 123,45 EUR";
		assertEquals(string, output);
	}

}
