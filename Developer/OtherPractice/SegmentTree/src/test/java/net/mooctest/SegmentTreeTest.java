package net.mooctest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SegmentTreeTest {

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
        assertEquals(8855, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test()
    public void testFlatSegmentTree() {
        try {
            new FlatSegmentTree(new ArrayList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List data = new ArrayList();

        try {
            new FlatSegmentTree(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SegmentTree.Data.QuadrantData quadrantData = new SegmentTree.Data.QuadrantData(1, 2);
        SegmentTree.Data.QuadrantData quadrantData2 = new SegmentTree.Data.QuadrantData(4, 3);
        SegmentTree.Data.RangeMaximumData rangeMaximumData = new SegmentTree.Data.RangeMaximumData(6, 8);
        SegmentTree.Data.RangeMaximumData rangeMaximumData1 = new SegmentTree.Data.RangeMaximumData(1, 3);
        SegmentTree.Data.RangeMinimumData rangeMinimumData = new SegmentTree.Data.RangeMinimumData(11, 9);
        SegmentTree.Data.RangeMinimumData rangeMinimumData1 = new SegmentTree.Data.RangeMinimumData(9, 11);
        SegmentTree.Data.RangeSumData rangeSumData = new SegmentTree.Data.RangeSumData(13, 16);
        SegmentTree.Data.RangeSumData rangeSumData1 = new SegmentTree.Data.RangeSumData(14, 18);
        SegmentTree.Data.QuadrantData quadrantData1 = new SegmentTree.Data.QuadrantData(17, 20, 0, 1, 2);

        System.out.println(quadrantData1.query(1, 4));
        System.out.println(quadrantData1.query(111, 1));

        data.add(quadrantData2);

        DynamicSegmentTree dynamicSegmentTree1 = new DynamicSegmentTree(data);
        dynamicSegmentTree1.root = null;
        for (int i = -5; i < 5; i++) {
            for (int j = -5; j < 5; j++) {
                try {
                    System.out.println(dynamicSegmentTree1.query(i, j));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        List data1 = new ArrayList();
        data1.add(rangeSumData);
        FlatSegmentTree flatSegmentTree3 = new FlatSegmentTree(data);
        flatSegmentTree3.root = null;
        for (int i = -12; i < 18; i++) {
            for (int j = -12; j < 18; j++) {
                try {
                    System.out.println(flatSegmentTree3.query(i, j));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        data.add(rangeMinimumData);
        data.add(rangeMaximumData);
        data.add(rangeSumData);
        data.add(quadrantData2);

        FlatSegmentTree flatSegmentTree = new FlatSegmentTree(data);
        System.out.println(flatSegmentTree.query(12));
        System.out.println(flatSegmentTree.query(1, 8));
        System.out.println(flatSegmentTree.query(-5, 30));
        System.out.println(flatSegmentTree.toString());

        for (int i = -5; i < 5; i++) {
            for (int j = -5; j < 5; j++) {
                System.out.println(flatSegmentTree.query(i, j));
                System.out.println(flatSegmentTree.query(j));
            }
        }

        try {
            new DynamicSegmentTree(new ArrayList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        DynamicSegmentTree dynamicSegmentTree = new DynamicSegmentTree(data);
        System.out.println(dynamicSegmentTree.query(1, 30));
        System.out.println(dynamicSegmentTree.toString());

        for (int i = -5; i < 5; i++) {
            for (int j = -5; j < 5; j++) {
                try {
                    System.out.println(dynamicSegmentTree.query(i, j));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        data.add(new SegmentTree.Data.QuadrantData(1, 4));
        try {
            new FlatSegmentTree(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        quadrantData.combined(quadrantData);
        quadrantData.combined(quadrantData1);
        quadrantData.combined(quadrantData2);
        System.out.println(quadrantData.hashCode());
        quadrantData2.equals(quadrantData1);
        quadrantData2.equals(quadrantData2);
        quadrantData2.equals(quadrantData);
        quadrantData2.equals(rangeMaximumData);

        rangeMaximumData.combined(rangeMaximumData);
        rangeMaximumData.combined(rangeMaximumData1);
        System.out.println(rangeMaximumData.query(1, 2));
        System.out.println(rangeMaximumData.query(100, 200));

        rangeMaximumData.combined(rangeMaximumData);
        rangeMaximumData.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData);

        rangeMaximumData1.maximum = new BigDecimal("20");
        rangeMaximumData.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData);

        rangeMaximumData1.maximum = null;
        rangeMaximumData.maximum = new BigDecimal("10");
        rangeMaximumData.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData);

        rangeMaximumData1.maximum = new BigDecimal("20");
        rangeMaximumData.maximum = new BigDecimal("10");
        rangeMaximumData.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData);

        rangeMaximumData1.maximum = new BigInteger("20");
        rangeMaximumData.maximum = new BigInteger("10");
        rangeMaximumData.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData1);

        rangeMaximumData1.maximum = Long.MAX_VALUE;
        rangeMaximumData.maximum = new Long("10");

        rangeMaximumData1.combined(rangeMaximumData1);
        rangeMaximumData.combined(rangeMaximumData1);

        rangeMaximumData1.maximum = Double.MAX_VALUE;
        rangeMaximumData.maximum = new Double("10");
        rangeMaximumData1.combined(rangeMaximumData1);
        rangeMaximumData.combined(rangeMaximumData1);

        rangeMaximumData1.maximum = Float.MAX_VALUE;
        rangeMaximumData.maximum = new Float("10");
        rangeMaximumData1.combined(rangeMaximumData1);
        rangeMaximumData.combined(rangeMaximumData1);


        rangeMaximumData1.maximum = 20;
        rangeMaximumData.maximum = 10;
        rangeMaximumData.combined(rangeMaximumData);
        rangeMaximumData.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData1);
        rangeMaximumData1.combined(rangeMaximumData);

        rangeMinimumData.combined(rangeMinimumData);
        System.out.println(rangeMaximumData.hashCode());
        System.out.println(rangeMaximumData.toString());
        rangeMaximumData.equals(rangeMaximumData);
        rangeMaximumData.equals(rangeMaximumData1);
        rangeMaximumData.equals(111);
        rangeMaximumData.equals(new SegmentTree.Data.RangeMaximumData(1, 2));


        rangeMinimumData.combined(rangeMaximumData);
        rangeMinimumData.combined(rangeMaximumData1);
        rangeMinimumData.combined(rangeMaximumData);

        rangeMinimumData1.minimum = new BigDecimal("20");
        rangeMinimumData.minimum = new BigDecimal("10");
        rangeMinimumData1.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);

        rangeMinimumData1.minimum = new BigInteger("20");
        rangeMinimumData.minimum = new BigInteger("10");
        rangeMinimumData1.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);


        rangeMinimumData1.minimum = Long.MAX_VALUE;
        rangeMinimumData.minimum = new Long("10");
        rangeMinimumData1.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);

        rangeMinimumData1.minimum = Double.MAX_VALUE;
        rangeMinimumData.minimum = new Double("10");
        rangeMinimumData1.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);

        rangeMinimumData1.minimum = Float.MAX_VALUE;
        rangeMinimumData.minimum = new Float("10");
        rangeMinimumData1.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);


        rangeMinimumData1.minimum = 20;
        rangeMinimumData.minimum = 10;
        rangeMinimumData.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);
        rangeMinimumData1.combined(rangeMinimumData1);
        rangeMinimumData1.combined(rangeMinimumData);

        rangeMinimumData1.minimum = null;
        rangeMinimumData.minimum = 10;
        rangeMinimumData.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);
        rangeMinimumData1.combined(rangeMinimumData1);
        rangeMinimumData1.combined(rangeMinimumData);

        rangeMinimumData1.minimum = 20;
        rangeMinimumData.minimum = null;
        rangeMinimumData.combined(rangeMinimumData);
        rangeMinimumData.combined(rangeMinimumData1);
        rangeMinimumData1.combined(rangeMinimumData1);
        rangeMinimumData1.combined(rangeMinimumData);


        rangeMinimumData.equals(rangeMinimumData);
        rangeMinimumData.equals(rangeMinimumData1);
        rangeMinimumData.equals(111);

        System.out.println(rangeMinimumData.hashCode());

        rangeMinimumData.query(1, 2);
        rangeMinimumData.query(100, 200);

        rangeSumData.combined(rangeSumData1);
        rangeSumData.combined(rangeSumData);
        rangeSumData1.combined(rangeSumData);

        rangeSumData.sum = new BigDecimal("20");
        rangeSumData1.sum = new BigDecimal("10");
        rangeSumData1.combined(rangeSumData);
        rangeSumData.combined(rangeSumData1);

        rangeSumData1.sum = new BigInteger("20");
        rangeSumData.sum = new BigInteger("10");
        rangeSumData1.combined(rangeSumData);
        rangeMinimumData.combined(rangeSumData1);

        rangeSumData.query(1, 2);
        rangeSumData.query(100, 200);

        rangeSumData1.sum = new Long("20");
        rangeSumData.sum = new Long("10");
        rangeSumData1.combined(rangeSumData);
        rangeSumData.combined(rangeSumData1);
        rangeSumData.combined(rangeMaximumData);

        rangeSumData1.sum = new Double("20");
        rangeSumData.sum = new Double("10");
        rangeSumData1.combined(rangeSumData);
        rangeSumData.combined(rangeSumData1);

        rangeSumData1.sum = null;
        rangeSumData.sum = new Double("10");
        rangeSumData1.combined(rangeSumData);
        rangeSumData.combined(rangeSumData1);

        rangeSumData1.sum = new Double("20");
        rangeSumData.sum = null;
        rangeSumData1.combined(rangeSumData);
        rangeSumData.combined(rangeSumData1);

        rangeSumData1.sum = new Float("20");
        rangeSumData.sum = new Float("10");
        rangeSumData1.combined(rangeSumData);
        rangeSumData.combined(rangeSumData1);


        rangeSumData1.sum = 20;
        rangeSumData.sum = 10;
        rangeSumData.combined(rangeSumData);
        rangeSumData.combined(rangeSumData1);
        rangeSumData1.combined(rangeSumData1);
        rangeSumData1.combined(rangeSumData);
        System.out.println(rangeSumData.hashCode());
        System.out.println(rangeSumData.toString());

        rangeSumData.equals(rangeSumData);
        rangeSumData.equals(new SegmentTree.Data.RangeSumData(1, 2));
        rangeSumData.equals(rangeSumData1);
        rangeSumData.equals(rangeMaximumData);


        SegmentTree.Data.IntervalData intervalData = new SegmentTree.Data.IntervalData(1, 2);
        SegmentTree.Data.IntervalData intervalData1 = new SegmentTree.Data.IntervalData(4, 2);
        System.out.println(intervalData.query(1, 2));
        System.out.println(intervalData.query(100, 200));
        System.out.println(intervalData.getData());
        System.out.println(intervalData.query(-13, -7));
        intervalData.combined(intervalData);
        intervalData.combined(rangeMaximumData);
        intervalData.combined(intervalData1);
        System.out.println(intervalData.hashCode());
        System.out.println(intervalData.toString());
        intervalData.equals(intervalData);
        intervalData.equals(intervalData1);
        intervalData.equals(rangeMaximumData);


        flatSegmentTree3.root = null;
        System.out.println(flatSegmentTree3.toString());

        flatSegmentTree.query(2, 2);

    }
}
