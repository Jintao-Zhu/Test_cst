package net.mooctest;

import org.junit.Test;

import net.mooctest.StringFunction;

import static org.junit.Assert.*;

public class StringFunctionTest {

    @Test(timeout = 4000)
    public void test(){
        StringFunction.isPalindromeInPlace("");
    }
}
