package net.mooctest;

import org.junit.*;

import java.util.ArrayList;

import static net.mooctest.BPlusTree.RangePolicy.EXCLUSIVE;
import static net.mooctest.BPlusTree.RangePolicy.INCLUSIVE;
import static org.junit.Assert.assertEquals;

public class BPlusTreeTest {

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
	public void testCalculatePower() {
		long res = BinaryExponentiation.calculatePower(5, 6);
		assertEquals(15625, res);
	}

	@Test
	public void testPower() {
		BinaryExponentiation exp = new BinaryExponentiation();
		long res = exp.power(5, 6);
		assertEquals(15625, res);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBPlusTree() {
		BPlusTree tree = new BPlusTree(2);
	}

	@Test()
	public void testInsertValue() {
		BPlusTree tree = new BPlusTree(4);
		tree.insert(0, "a");
		tree.insert(1, "b");
		tree.insert(2, "c");
		tree.insert(3, "d");
		tree.insert(5, "e");
		tree.insert(4, "f");
		String result = tree.toString();
		String expected = "{[2, 4]}\n" +
				"{[0, 1], [2, 3], [4, 5]}\n";
		assertEquals(expected, result);

		tree.delete(1);
		tree.delete(4);
		tree.delete(4);
		tree.delete(2);
		tree.delete(3);
		tree.delete(0);
		tree.delete(0);

		assertEquals(null, tree.search(1));
		assertEquals(null, tree.search(2));
	}

	@Test()
	public void testSearchRange() {
		BPlusTree tree = new BPlusTree(4);

		tree.insert(0, "a");
		tree.insert(1, "b");
		tree.insert(2, "c");
		tree.insert(3, "d");
		tree.insert(5, "e");
		tree.insert(4, "f");

		tree.searchRange(0, EXCLUSIVE, 4, INCLUSIVE);
		tree.searchRange(0, INCLUSIVE, 4, INCLUSIVE);
		tree.searchRange(0, EXCLUSIVE, 4, EXCLUSIVE);


	}

	@Test()
	public void testXPartition() {
		ClosestPair.main(null);
	}

	@Test
	public void testMaximumMinimumWindow() {
		try {
			MaximumMinimumWindow.main(new String[0]);
		} catch (AssertionError e) {}
	}


	@Test()
	public void testSkylineAlgorithm() {
		ArrayList<SkylineAlgorithm.Point> arrayList = new ArrayList<>();
		arrayList.add(new SkylineAlgorithm.Point(0, 0));
		arrayList.add(new SkylineAlgorithm.Point(1, 3));
		arrayList.add(new SkylineAlgorithm.Point(-2, 2));
		arrayList.add(new SkylineAlgorithm.Point(2, -22));
		arrayList.add(new SkylineAlgorithm.Point(-52, -22));
		SkylineAlgorithm alg = new SkylineAlgorithm();
		System.out.println(alg.produceSubSkyLines(arrayList).toString());
	}

	@Test()
	public void testProduceFinalSkyLine() {
		ArrayList<SkylineAlgorithm.Point> arrayList = new ArrayList<>();
		arrayList.add(new SkylineAlgorithm.Point(0, 0));
		arrayList.add(new SkylineAlgorithm.Point(1, 3));
		arrayList.add(new SkylineAlgorithm.Point(-2, 2));
		arrayList.add(new SkylineAlgorithm.Point(2, -22));
		arrayList.add(new SkylineAlgorithm.Point(-52, -22));
		ArrayList<SkylineAlgorithm.Point> arrayList1 = new ArrayList<>();
		arrayList1.add(new SkylineAlgorithm.Point(0, 0));
		arrayList1.add(new SkylineAlgorithm.Point(1, 3));
		arrayList1.add(new SkylineAlgorithm.Point(-2, 2));
		arrayList1.add(new SkylineAlgorithm.Point(2, -22));
		arrayList1.add(new SkylineAlgorithm.Point(-52, -22));
		SkylineAlgorithm alg = new SkylineAlgorithm();
		System.out.println(alg.produceFinalSkyLine(arrayList, arrayList1).toString());
	}

	@Test()
	public void testStrassenMatrixMultiplication() {
		StrassenMatrixMultiplication alg = new StrassenMatrixMultiplication();
		int [][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
		int [][] b = {{1, 2, 3, 4}, {5, -6, 7, 8}, {9, 10, -11, 12}, {-13, 14, 15, 16}};
		assertEquals(-14, alg.multiply(a, b)[0][0]);
		assertEquals(0, alg.sub(a, b)[0][0]);
		assertEquals(2, alg.add(a, b)[0][0]);
		alg.split(a, b, 0, 0);
		alg.join(a, b, 0, 0);
	}



}