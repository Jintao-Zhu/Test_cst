package net.mooctest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.mooctest.OST;

import static org.junit.Assert.*;
import org.junit.Before;
import org.omg.CORBA.INTERNAL;

public class OSTTest {

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
        assertEquals(322, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }
     
    @Test(timeout = 4000)
    public void test() {
        OST<Integer> tree = new OST<>();
//        TreeSet<Integer> set = new TreeSet<>();

        System.out.println(tree.indexOf(1));
        System.out.println(tree.isEmpty());
        System.out.println(tree.isHealthy());
        try {
            System.out.println(tree.iterator().next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(tree.iterator().hasNext());
        System.out.println(tree.add(-1));
        System.out.println(tree.add(0));

        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(8);
        tree.add(9);
        tree.add(1);

        System.out.println(tree.iterator());
        System.out.println(tree.iterator().hasNext());
        System.out.println(tree.iterator().next());


        tree.remove(1);
        tree.remove(-1);
        tree.remove(2);
        tree.remove(3);
        tree.remove(0);

        tree.add(1);
        tree.add(2);
        tree.add(3);


        System.out.println(tree.toArray());
        Integer[] array = new Integer[5];
        System.out.println(tree.toArray(array));
        Integer[] array1 = new Integer[10];
        System.out.println(tree.toArray(array1));
        Integer[] array2 = new Integer[2];
        System.out.println(tree.toArray(array2));

        System.out.println(tree.contains(1));

        Collection<Integer> collection = new HashSet<>();
        collection.add(3);
        collection.add(2);
        collection.add(4);
        collection.add(1);

        System.out.println(tree.containsAll(collection));
        System.out.println(tree.addAll(collection));
        System.out.println(tree.retainAll(collection));

        System.out.println(tree.get(1));
        System.out.println(tree.get(2));
        try {
            System.out.println(tree.get(4));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(tree.get(0));
        try {
            System.out.println(tree.get(-1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(tree.get(5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(tree.indexOf(i));
        }

        System.out.println(tree.size());
        System.out.println(tree.isEmpty());
        System.out.println(tree.isHealthy());
//
//        tree.remove(1);
//        tree.remove(-1);
//        tree.remove(2);
//        tree.remove(3);
//        tree.remove(0);

        tree.removeAll(collection);

        tree.clear();
    }
}
