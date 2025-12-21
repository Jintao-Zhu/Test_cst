package net.mooctest;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static net.mooctest.FibonacciHeap.MAX_CAPACITY;
import static org.junit.Assert.*;


public class TestFibonacciHeap {

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
		assertEquals(420, allOutput.length(), 20);
	}

	private static String standardizeOutput(String output) {
		return output
				.replaceAll("\n", "")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "")
				.replaceAll("[^a-zA-Z0-9]", "");
	}

	@Test
	public void test() {

		Set<Integer> nodes = new HashSet<Integer>();
		nodes.add(1);
		nodes.add(2);
		nodes.add(3);
		nodes.add(4);
        List<Weighted<Edge<Integer>>> edges = Arrays.asList(
                new Weighted<>(new Edge<>(1, 2), 2.5),
                new Weighted<>(new Edge<>(2, 3), 3.0),
                new Weighted<>(new Edge<>(3, 4), 4.0),
                new Weighted<>(new Edge<>(1, 4), 1.5)
		);
		Integer root = 1;
		double[][] weights = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
		SparseWeightedGraph<Integer> graph1 = SparseWeightedGraph.from(nodes, edges);
		SparseWeightedGraph<Integer> graph2 = SparseWeightedGraph.from(edges);
		DenseWeightedGraph<Integer> graph3 = DenseWeightedGraph.from(nodes, weights);
		DenseWeightedGraph<Integer> graph4 = DenseWeightedGraph.from(weights);

		List<Weighted<Arborescence<Integer>>> kBestArborescences = KBestArborescences.getKBestArborescences(graph1, root, 1);

		KBestArborescences.getKBestArborescences(graph2, root, 2);
		KBestArborescences.getKBestArborescences(graph3, root, 3);
		KBestArborescences.getKBestArborescences(graph4, root, 4);
		System.out.println(KBestArborescences.getKBestArborescences(graph4, root, 0));

		System.out.println(graph1.getWeightOf(1, 2));
		System.out.println(graph1.getWeightOf(2, 5));
		System.out.println(graph1.getWeightOf(5, 2));
		System.out.println(graph1.getWeightOf(0, 0));
		System.out.println(graph1.getIncomingEdges(-1));

		System.out.println(graph3.getWeightOf(1, 2));
		System.out.println(graph3.getWeightOf(2, 5));
		System.out.println(graph3.getWeightOf(5, 2));
		System.out.println(graph3.getIncomingEdges(-1));

		ImmutableMap<Integer, Integer> parents = ImmutableMap.of(1, 2, 2, 3, 3, 4);
		ImmutableMap<Integer, Integer> parents1 = ImmutableMap.of(1, 2, 2, 3);
		Arborescence<Integer> arborescence = Arborescence.of(parents);
		System.out.println(arborescence.contains(new Edge<>(1, 1)));
		System.out.println(arborescence.contains(new Edge<>(0, 0)));
		System.out.println(arborescence.contains(new Edge<>(2, 1)));
		System.out.println(arborescence.toString());
		System.out.println(arborescence.equals(arborescence));
		System.out.println(arborescence.equals(Arborescence.of(parents1)));
		System.out.println(arborescence.equals(Arborescence.of(ImmutableMap.of(1, 2, 2, 3, 3, 4))));
		System.out.println(arborescence.equals(Arborescence.of(ImmutableMap.of(1, 2, 2, 2, 3, 4))));
		System.out.println(arborescence.equals(null));
		System.out.println(arborescence.equals(1));
		System.out.println(arborescence.hashCode());

		nodes.add(5);
		try {
			DenseWeightedGraph.from(nodes, weights);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		System.out.println(edges.toString());
		Edge edge = new Edge(1, 2);
		System.out.println(edge.equals(null));
		System.out.println(edge.equals(1));
	}

	@Test
	public void testFibonacci() {
		FibonacciHeap<Integer, Integer> heap = FibonacciHeap.create();
		FibonacciHeap<Integer, Integer> heap1 = FibonacciHeap.create();
		heap.add(1, 1);
		heap.add(2, 2);
		heap.add(3, 3);
		heap.clear();
		System.out.println();
		FibonacciHeap.merge(heap, heap1);
		heap.add(1, 1);
		System.out.println(heap.isEmpty());
	}

	@Test
	public void testFibonacciHeap2() throws NoSuchFieldException {
		FibonacciHeap<Integer, Integer> heap = FibonacciHeap.create();
		FibonacciQueue<Integer> queue = FibonacciQueue.create();
		System.out.println(queue.peek());
		Optional<FibonacciHeap<Integer, Integer>.Entry> entryOptional = heap.add(4, 10);
		FibonacciHeap<Integer, Integer>.Entry entry = entryOptional.get();
		try {
			heap.decreasePriority(entry, 15);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		heap.decreasePriority(entry, 8);
		Optional<FibonacciHeap<Integer, Integer>.Entry> entryOptional2 = heap.add(5, 20);
		FibonacciHeap<Integer, Integer>.Entry entry2 = entryOptional2.get();
		heap.decreasePriority(entry2, 5);
		System.out.println(heap.isEmpty());
		heap.remove(entry2);
		heap.clear();
		System.out.println(heap.isEmpty());
		System.out.println(heap.pollOption());

		Field field = FibonacciHeap.class.getDeclaredField("size");
		field.setAccessible(true);
		try {
			field.setInt(heap, MAX_CAPACITY);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			heap.add(1, 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testPair() {
		Pair<Integer, Integer> pair = Pair.of(1, 2);
		Pair<Integer, Integer> pair1 = Pair.of(1, 1);
		Pair<Integer, Integer> pair2 = Pair.of(2, 2);
		System.out.println(pair.equals(pair));
		System.out.println(pair.equals(pair1));
		System.out.println(pair.equals(pair2));
		System.out.println(pair.equals(null));
		System.out.println(pair.equals(1));
		System.out.println(pair.hashCode());
		System.out.println(pair.toString());
	}

	@Test
	public void testWeighted() {
		Weighted<Integer> weighted = Weighted.weighted(1, 2);
		Weighted<Integer> weighted1 = Weighted.weighted(2, 2);
		Weighted<Integer> weighted2 = Weighted.weighted(1, 1);
		System.out.println(weighted.equals(weighted));
		System.out.println(weighted.equals(weighted1));
		System.out.println(weighted.equals(weighted2));
		System.out.println(weighted.equals(null));
		System.out.println(weighted.equals(1));
		System.out.println(weighted.hashCode());
	}

	@Test
	public void testCutAndMakeRootWithParentMarked() throws Exception {
		FibonacciHeap<String, Integer> heap = FibonacciHeap.create();
		FibonacciHeap<String, Integer>.Entry grandParent = heap.add("GrandParent", 5).get();
		FibonacciHeap<String, Integer>.Entry parent = heap.add("Parent", 10).get();
		FibonacciHeap<String, Integer>.Entry child = heap.add("Child", 20).get();
		Field oParentField = FibonacciHeap.Entry.class.getDeclaredField("oParent");
		Field oFirstChildField = FibonacciHeap.Entry.class.getDeclaredField("oFirstChild");
		Field degreeField = FibonacciHeap.Entry.class.getDeclaredField("degree");
		Field isMarkedField = FibonacciHeap.Entry.class.getDeclaredField("isMarked");
		oParentField.setAccessible(true);
		oFirstChildField.setAccessible(true);
		degreeField.setAccessible(true);
		isMarkedField.setAccessible(true);
		degreeField.set(grandParent, 1);
		oFirstChildField.set(grandParent, Optional.of(parent));
		oParentField.set(parent, Optional.of(grandParent));
		degreeField.set(parent, 1);
		oFirstChildField.set(parent, Optional.of(child));
		oParentField.set(child, Optional.of(parent));
		isMarkedField.set(parent, true);
		Method cutAndMakeRootMethod = FibonacciHeap.class.getDeclaredMethod("cutAndMakeRoot", FibonacciHeap.Entry.class);
		cutAndMakeRootMethod.setAccessible(true);
		cutAndMakeRootMethod.invoke(heap, child);
	}

	@Test
	public void testCutAndMakeRootWithParentNotMarked() throws Exception {
		FibonacciHeap<String, Integer> heap = FibonacciHeap.create();
		FibonacciHeap<String, Integer>.Entry parent = heap.add("Parent", 10).get();
		FibonacciHeap<String, Integer>.Entry child = heap.add("Child", 20).get();
		Field oParentField = FibonacciHeap.Entry.class.getDeclaredField("oParent");
		Field oFirstChildField = FibonacciHeap.Entry.class.getDeclaredField("oFirstChild");
		Field degreeField = FibonacciHeap.Entry.class.getDeclaredField("degree");
		Field isMarkedField = FibonacciHeap.Entry.class.getDeclaredField("isMarked");
		oParentField.setAccessible(true);
		oFirstChildField.setAccessible(true);
		degreeField.setAccessible(true);
		isMarkedField.setAccessible(true);
		degreeField.set(parent, 1);
		oFirstChildField.set(parent, Optional.of(child));
		oParentField.set(child, Optional.of(parent));
		isMarkedField.set(parent, false);
		Method cutAndMakeRootMethod = FibonacciHeap.class.getDeclaredMethod("cutAndMakeRoot", FibonacciHeap.Entry.class);
		cutAndMakeRootMethod.setAccessible(true);
		cutAndMakeRootMethod.invoke(heap, child);
	}

	@Test
	public void testCutAndMakeRootWithParentFirstChildNext() throws Exception {
		FibonacciHeap<String, Integer> heap = FibonacciHeap.create();
		FibonacciHeap<String, Integer>.Entry parent = heap.add("Parent", 10).get();
		FibonacciHeap<String, Integer>.Entry child = heap.add("Child", 20).get();
		FibonacciHeap<String, Integer>.Entry nextChild = heap.add("NextChild", 30).get();
		Field oParentField = FibonacciHeap.Entry.class.getDeclaredField("oParent");
		Field oFirstChildField = FibonacciHeap.Entry.class.getDeclaredField("oFirstChild");
		Field degreeField = FibonacciHeap.Entry.class.getDeclaredField("degree");
		Field isMarkedField = FibonacciHeap.Entry.class.getDeclaredField("isMarked");
		oParentField.setAccessible(true);
		oFirstChildField.setAccessible(true);
		degreeField.setAccessible(true);
		isMarkedField.setAccessible(true);
		degreeField.set(parent, 2);
		oFirstChildField.set(parent, Optional.of(child));
		oParentField.set(child, Optional.of(parent));
		oParentField.set(nextChild, Optional.of(parent));
		Field nextField = FibonacciHeap.Entry.class.getDeclaredField("next");
		nextField.setAccessible(true);
		nextField.set(child, nextChild);
		Method cutAndMakeRootMethod = FibonacciHeap.class.getDeclaredMethod("cutAndMakeRoot", FibonacciHeap.Entry.class);
		cutAndMakeRootMethod.setAccessible(true);
		cutAndMakeRootMethod.invoke(heap, child);
	}

	@Test
	public void testCutAndMakeRoot() throws Exception {
		FibonacciHeap<String, Integer> heap = FibonacciHeap.create();
		FibonacciHeap<String, Integer>.Entry parent = heap.add("Parent", 10).get();
		FibonacciHeap<String, Integer>.Entry child = heap.add("Child", 20).get();
		Field oParentField = FibonacciHeap.Entry.class.getDeclaredField("oParent");
		oParentField.setAccessible(true);
		oParentField.set(child, Optional.of(parent));
		Field oFirstChildField = FibonacciHeap.Entry.class.getDeclaredField("oFirstChild");
		oFirstChildField.setAccessible(true);
		oFirstChildField.set(parent, Optional.of(child));
		Field degreeField = FibonacciHeap.Entry.class.getDeclaredField("degree");
		degreeField.setAccessible(true);
		degreeField.set(parent, 1);
		Method cutAndMakeRootMethod = FibonacciHeap.class.getDeclaredMethod("cutAndMakeRoot", FibonacciHeap.Entry.class);
		cutAndMakeRootMethod.setAccessible(true);
		cutAndMakeRootMethod.invoke(heap, child);
	}

}
