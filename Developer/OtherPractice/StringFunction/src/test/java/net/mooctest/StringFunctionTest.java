package net.mooctest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.mooctest.StringFunction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class StringFunctionTest {

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
        assertEquals(88, allOutput.length(), 20);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test()
    public void test(){
        System.out.println(StringFunction.reverseWordsByCharWithAdditionalStorage("hello world"));
        System.out.println(StringFunction.reverseWordsInPlace("hello world"));
        System.out.println(StringFunction.reverseWordsInPlace("hello worlds"));
        System.out.println(StringFunction.reverseWordsInPlace("hello word"));
        System.out.println(StringFunction.reverseWordsInPlace("hello"));
        System.out.println(StringFunction.isPalindromeInPlace("hello world"));
        System.out.println(StringFunction.isPalindromeInPlace("abcba"));
        System.out.println(StringFunction.generateSubsets("hello world"));
        System.out.println(StringFunction.levenshteinDistanceIterative("hello world", "world"));
        System.out.println(StringFunction.levenshteinDistanceRecursive("hello world", "world"));
    }
    @Test
    public void testManager() {
        System.out.println(Manacher.getLongestPalindromicSubstring("hello world"));
        System.out.println(Manacher.getLongestPalindromicSubstring("hello wooorld"));
        System.out.println(Manacher.getLongestPalindromicSubstring(null));
        assertEquals("", Manacher.getLongestPalindromicSubstring(""));
    }
}
