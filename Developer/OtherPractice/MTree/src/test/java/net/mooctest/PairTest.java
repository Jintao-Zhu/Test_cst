
package net.mooctest;

import org.junit.*;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class PairTest {

    class DistanceFunctionMock implements DistanceFunction<Object> {
        @Override
        public double calculate(Object data1, Object data2) {
            return 0.0;
        }
    }

    class SplitFunctionMock implements SplitFunction<Object> {
        @Override
        public SplitResult<Object> process(Set<Object> objects, DistanceFunction<? super Object> distanceFunction) {
            return null;
        }
    }

    private DistanceFunction<Object> distanceFunction;
    private SplitFunction<Object> splitFunction;

    @Before
    public void setUp() {
        distanceFunction = new DistanceFunctionMock();
        splitFunction = new SplitFunctionMock();
    }

    @Test
    public void testConstructor1() {
        MTree<Object> mTree = new MTree<>(distanceFunction, splitFunction);
        assertNotNull(mTree.toString(), "MTree instance should be created successfully.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        MTree<Object> mTree = new MTree<>(1, 7, distanceFunction, splitFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() {
        MTree<Object> mTree = new MTree<>(3, 0, distanceFunction, splitFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor4() {
        MTree<Object> mTree = new MTree<>(3, 0, null, splitFunction);
    }

    @Test
    public void testConstructorWithValidParameters() {
        MTree<Object> mTree = new MTree<>(3, 7, distanceFunction, splitFunction);
        assertNotNull(mTree.toString(), "MTree instance should be created successfully.");
    }


    @Test
    public void testConstructorWithNullSplitFunction() {
        MTree<Object> mTree = new MTree<>(3, 7, distanceFunction, null);
        assertNotNull(mTree.toString(), "MTree instance should be created with default SplitFunction.");
    }

    @Test
    public void testAddRemove() {
        MTree<Object> mTree = new MTree<>(3, 7, distanceFunction, null);
        assertFalse(mTree.remove(1));
        mTree.add("A");
        mTree.add("B");
        try {
            mTree.add("B");
        } catch (AssertionError e) {}
        assertTrue(mTree.remove("A"));
        try {
            mTree.remove("0");
        } catch (Exception e) {}
        assertFalse( mTree.remove("1"));
    }

    @Test
    public void testGet() {
        MTree<Object> mTree = new MTree<>(3, 7, distanceFunction, null);
        mTree._check();
        mTree.add("A");
        mTree.add("B");
        mTree.add("C");
        mTree.add("D");
        mTree.getNearest("A");
        mTree.getNearest("B", 10.0, 5);
        mTree.getNearestByLimit("C", 5);
        mTree.getNearestByRange("D", 10.0);
        mTree._check();
    }

    @Test
    public void testPair() {
        Pair<Object> pair = new Pair<>();
        try {
            pair.get(-1);
        } catch (IllegalArgumentException e) {};
        Pair<Object> pair2 = new Pair<>("1", "2");
        assertEquals("1", pair2.get(0));
        assertEquals("2", pair2.get(1));
    }


    @Test
    public void testUtils() {
        List<Integer> items = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        Pair<Integer> result = Utils.minMax(items);
        assertNotNull(result.toString(), "Result should not be null");
        assertEquals("1", result.first.toString());
        assertEquals("9", result.second.toString());
    }

    @Test
    public void testMinMaxWithEmptyInput() {
        List<Integer> items = Collections.emptyList();
        Pair<Integer> result = Utils.minMax(items);
        assertNull(result);
    }

    @Test
    public void testMinMaxWithSingleElement() {
        List<Integer> items = Collections.singletonList(42);
        Pair<Integer> result = Utils.minMax(items);
        assertNotNull(result);
        assertEquals("42", result.first.toString());
        assertEquals("42", result.second.toString());
    }

    @Test
    public void testRandomSampleWithValidInput() {
        List<String> items = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> sample = Utils.randomSample(items, 2);
        assertEquals(2, sample.size());
        for (String item : sample) {
            assertTrue(items.contains(item));
        }
    }

    @Test
    public void testRandomSampleWithMoreThanAvailable() {
        List<String> items = Arrays.asList("apple", "banana");
        List<String> sample = Utils.randomSample(items, 5);
        assertEquals(2, sample.size());
    }

    @Test
    public void testRandomSampleWithZero() {
        List<String> items = Arrays.asList("apple", "banana", "cherry");
        List<String> sample = Utils.randomSample(items, 0);
        assertEquals(0, sample.size());
    }

    @Test
    public void testRandomSampleWithEmptyCollection() {
        List<String> items = Collections.emptyList();
        List<String> sample = Utils.randomSample(items, 3);
        assertEquals(0, sample.size());
    }

    private static class MockDistanceFunction implements DistanceFunction<String> {
        @Override
        public double calculate(String a, String b) {
            return a.length() - b.length(); // 简单的距离计算示例（根据字符串长度）
        }
    }

    @Test
    public void testBalancedPartitionProcess() {
        Set<String> dataSet = new HashSet<>();
        Collections.addAll(dataSet, "apple", "banana", "kiwi", "cherry");

        Pair<String> promoted = new Pair<>("apple", "banana");
        PartitionFunctions.BalancedPartition<String> partitionFunction = new PartitionFunctions.BalancedPartition<>();

        Pair<Set<String>> result = partitionFunction.process(promoted, dataSet, new MockDistanceFunction());

        assertNotNull(result);
        assertEquals(2, result.first.size());
        assertEquals(2, result.second.size());

        // 验证每个数据只在一个分区中
        Set<String> firstPartition = result.first;
        Set<String> secondPartition = result.second;
        for (String item : firstPartition) {
            assertFalse(secondPartition.contains(item));
        }
        for (String item : secondPartition) {
            assertFalse(firstPartition.contains(item));
        }
    }

    private static class TestEuclideanCoordinate implements DistanceFunctions.EuclideanCoordinate {

        @Override
        public int dimensions() {
            return 0;
        }

        @Override
        public double get(int index) {
            return 0;
        }
    }

    // Test Euclidean distance function directly
    @Test
    public void testEuclideanDistance() {
        TestEuclideanCoordinate coord1 = new TestEuclideanCoordinate() {
            public int dimensions() { return 3; }
            public double get(int index) { return index == 0 ? 1 : index == 1 ? 2 : 3; }
        };

        TestEuclideanCoordinate coord2 = new TestEuclideanCoordinate() {
            public int dimensions() { return 3; }
            public double get(int index) { return index == 0 ? 4 : index == 1 ? 5 : 6; }
        };

        double expected = Math.sqrt(27); // √((4-1)^2 + (5-2)^2 + (6-3)^2) = √27
        assertEquals(expected, DistanceFunctions.euclidean(coord1, coord2), 1e-9);
    }

    // Test Euclidean distance for Integer lists
    @Test
    public void testEuclideanDistanceIntegerList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        double expected = Math.sqrt(27);
        assertEquals(expected, DistanceFunctions.EUCLIDEAN_INTEGER_LIST.calculate(list1, list2), 1e-9);
    }

    // Test Euclidean distance for Double lists
    @Test
    public void testEuclideanDistanceDoubleList() {
        List<Double> list1 = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> list2 = Arrays.asList(4.0, 5.0, 6.0);

        double expected = Math.sqrt(27);
        assertEquals(expected, DistanceFunctions.EUCLIDEAN_DOUBLE_LIST.calculate(list1, list2), 1e-9);
    }

    // Test cached distance function
    @Test
    public void testCachedDistanceFunction() {
        DistanceFunction<List<Integer>> distanceFunction = DistanceFunctions.cached(DistanceFunctions.EUCLIDEAN_INTEGER_LIST);

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        // Call calculate twice to verify caching behavior
        double firstCalculation = distanceFunction.calculate(list1, list2);
        double secondCalculation = distanceFunction.calculate(list1, list2);

        double expected = Math.sqrt(27);
        assertEquals(expected, firstCalculation, 1e-9);
        assertEquals(expected, secondCalculation, 1e-9);
    }


}
