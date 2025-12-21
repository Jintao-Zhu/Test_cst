package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;

public class AlgorithmFactoryTest {

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
		assertEquals(839, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test
	public void testQuickSort() {
		AlgorithmFactory factory = new AlgorithmFactory();
		Algorithm algorithm = factory.getAlgorithm("QuickSort");
		try {
			algorithm = factory.getAlgorithm("test");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ArrayDataStructure data = new ArrayDataStructure(5);
		data.add(3);
		data.add(2);
		data.add(5);
		data.add(4);
		data.add(1);
		try {
			data.add(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.get(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.set(10, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		algorithm.sort(data);

		QuickSort quickSort = new QuickSort();
		quickSort.sort(data);
		System.out.println(quickSort.search(data, 3));
		System.out.println(quickSort.search(data, 10));
		System.out.println(quickSort.evaluatePerformance(data).getComparisons());
	}
	@Test
	public void testBubblesort() {
		AlgorithmFactory factory = new AlgorithmFactory();
		Algorithm algorithm = factory.getAlgorithm("bubblesort");
		ArrayDataStructure data = new ArrayDataStructure(5);
		data.add(3);
		data.add(2);
		data.add(5);
		data.add(4);
		data.add(1);
		try {
			data.add(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.get(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.set(10, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		algorithm.sort(data);

		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.sort(data);
		System.out.println(bubbleSort.search(data, 3));
		System.out.println(bubbleSort.search(data, 10));
		System.out.println(bubbleSort.evaluatePerformance(data).getComparisons());
	}
	@Test
	public void testParallelmergesort() {
		AlgorithmFactory factory = new AlgorithmFactory();
		Algorithm algorithm = factory.getAlgorithm("parallelmergesort");
		ArrayDataStructure data = new ArrayDataStructure(5);
		data.add(3);
		data.add(2);
		data.add(5);
		data.add(4);
		data.add(1);
		try {
			data.add(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.get(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.set(10, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		algorithm.sort(data);

		ParallelMergeSort parallelMergeSort = new ParallelMergeSort(1);
		parallelMergeSort.sort(data);
		System.out.println(parallelMergeSort.search(data, 3));
		System.out.println(parallelMergeSort.search(data, 10));
		System.out.println(parallelMergeSort.evaluatePerformance(data).getComparisons());
	}
	@Test
	public void testConcurrentAlgorithmManager() throws Exception {
		ConcurrentAlgorithmManager manager = new ConcurrentAlgorithmManager(2);
		manager.addAlgorithm(new BubbleSort());
		HashTableDataStructure data = new HashTableDataStructure();
		data.fromArray(new int[] { 3, 2, 5, 4, 1 });
		System.out.println(data.toArray());
		manager.parallelSort("Bubble Sort", data);
		try {
			manager.parallelSort("test", data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			manager.parallelSearch("Sort", data,1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ParallelMergeSort parallelMergeSort = new ParallelMergeSort(1);
		System.out.println(parallelMergeSort.search(data, 3));
		System.out.println(parallelMergeSort.search(data, 10));
		System.out.println(parallelMergeSort.evaluatePerformance(data).getComparisons());
		manager.parallelSearch("Bubble Sort", data,1);
	}
	@Test
	public void testDynamicAlgorithmManager() throws Exception {
		PerformanceTracker performanceTracker = new PerformanceTracker();
		DynamicAlgorithmManager manager = new DynamicAlgorithmManager(performanceTracker);
		manager.addAlgorithm(new BubbleSort());
		ArrayDataStructure data = new ArrayDataStructure(5);
		data.fromArray(new int[] { 3, 2, 5, 4, 1 });
		try {
			manager.autoSelectAndSort(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		ArrayDataStructure data2 = new ArrayDataStructure(1001);
		try {
			manager.autoSelectAndSort(data2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (int i = 0; i < 1001; i++) {
			data2.add(1000-i);
		}
		try {
			manager.autoSelectAndSort(data2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testAlgorithmManager() throws Exception {
		AlgorithmManager manager = new AlgorithmManager();
		manager.addAlgorithm(new BubbleSort());
		ArrayDataStructure data = new ArrayDataStructure(5);
		data.fromArray(new int[] { 3, 2, 5, 4, 1 });
		System.out.println(manager.searchData("Bubble Sort", data, 2));
		try {
			manager.searchData("Sort", data,1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			manager.sortData("test", data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		manager.sortData("Bubble Sort", data);

	}

	@Test
	public void testBSTDataStructure(){
		BSTDataStructure data = new BSTDataStructure();
		data.add(3);
		data.add(2);
		data.add(5);
		data.add(4);
		data.add(1);
		data.add(1);
		try {
			data.get(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.set(10, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data.toArray());
		data.fromArray(new int[] { 3, 2, 5, 4, 1 });

	}
	@Test
	public void testHeapDataStructure(){
		HeapDataStructure data = new HeapDataStructure();
		data.fromArray(new int[] { 3, 2, 5, 4, 1 });
		try {
			data.get(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			data.set(10, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data.toArray());

	}
	@Test
	public void testLinkedListDataStructure(){
		LinkedListDataStructure data = new LinkedListDataStructure();
		data.add(3);
		data.add(2);
		data.add(5);
		data.add(4);
		data.add(1);
		data.fromArray(new int[] { 3, 2, 5, 4, 1 });
		try {
			data.get(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data.get(2));
		try {
			data.set(10, 10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		data.set(2, 10);
		System.out.println(data.toArray());

	}
	@Test
	public void testMultiThreadedSearch() throws ExecutionException, InterruptedException, NoSuchFieldException {
		int[] data = new int[] { 3, 2, 5, 4, 1 };
		MultiThreadedSearch search = new MultiThreadedSearch(10);

//		System.out.println(search.parallelSearch(data, 2));
		search.linearSearch(data, 2, 0, 5);
		search.linearSearch(data, 10, 0, 5);

	}
	@Test
	public void testOptimizedQuickSort() {
		ArrayDataStructure data = new ArrayDataStructure(15);
		data.fromArray(new int[] { 9, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1 });
		OptimizedQuickSort quickSort = new OptimizedQuickSort();
		quickSort.sort(data);
		quickSort.sort(new ArrayDataStructure(0));
		System.out.println(quickSort.search(data, 3));
		System.out.println(quickSort.search(data, 10));
		System.out.println(quickSort.search(data, 1));
		System.out.println(quickSort.evaluatePerformance(data).getComparisons());

	}
	@Test
	public void testPerformanceTracker() {
		PerformanceTracker tracker = new PerformanceTracker();
		AlgorithmPerformance performance = new AlgorithmPerformance(10, 10, 10, 10, 10);
		tracker.trackPerformance("Bubble Sort", performance);
		tracker.generateReport();
		tracker.getBestPerformance();
		performance.report();
	}

}
