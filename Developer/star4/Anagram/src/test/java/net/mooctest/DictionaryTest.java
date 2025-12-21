package net.mooctest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.*;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

public class DictionaryTest{
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
        assertEquals(56, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test(timeout = 4000)
    public void test() throws IOException {

        File file = new File("src/test/resources/test.txt");
        FileWriter writer = new FileWriter("src/test/resources/test.txt");
        writer.write("test\nhello\nworld\n");
        writer.close();

        Dictionary dictionary = new Dictionary();
        Anagram anagram = new Anagram("src/test/resources/test.txt");
        Set<Set<String>> res1 = anagram.findAllAnagrams("111");
        Set<Set<String>> res2 = anagram.findAllAnagrams("test\nhello\nworld\n");
        Set<Set<String>> res3 = anagram.findAllAnagrams("test");

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

    }
}
