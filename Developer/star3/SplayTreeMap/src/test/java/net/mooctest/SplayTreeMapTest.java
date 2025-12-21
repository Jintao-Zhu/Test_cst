package net.mooctest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.SortedMap;

public class SplayTreeMapTest{

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static PrintStream originalOut;

//    @BeforeClass
//    public static void setUp() {
//        originalOut = System.out;
//        System.setOut(new PrintStream(outputStream));
//    }
//
//    @AfterClass
//    public static void tearDownAndVerify() {
//        System.setOut(originalOut);
//        String allOutput = standardizeOutput(outputStream.toString());
//        assertEquals(32, allOutput.length(), 20);
//    }
//
//    private static String standardizeOutput(String output) {
//        return output
//                .replaceAll("\n", "")
//                .replaceAll("\\s", "")
//                .replaceAll("\\p{C}", "")
//                .replaceAll("[^a-zA-Z0-9]", "");
//    }

    @Test()
    public void testSplayTreeMap()  throws Throwable  {
        SplayTreeMap splayTreeMap1 = new SplayTreeMap();
        splayTreeMap1.put(1, 1);
        splayTreeMap1.put(2, 2);
    }

}
