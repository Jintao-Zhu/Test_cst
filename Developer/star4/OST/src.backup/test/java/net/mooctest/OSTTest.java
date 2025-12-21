package net.mooctest;

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
import org.junit.Test;

import net.mooctest.OST;

import static org.junit.Assert.*;
import org.junit.Before;

public class OSTTest {
     
    @Test(timeout = 4000)
    public void test() {
    	OST<Integer> tree = new OST<>();
        TreeSet<Integer> set = new TreeSet<>();
        assertEquals(set.isEmpty(), tree.isEmpty());
        }
    
}
