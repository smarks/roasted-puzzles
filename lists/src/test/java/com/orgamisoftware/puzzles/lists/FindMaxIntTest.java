package com.orgamisoftware.puzzles.lists;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FindMaxIntTest {

    @Test
    public void testFindMaxIntSimple() {
        int[] values = new int[] {1,3,17,2,11};
        int maxIntSimple = FindMaxInt.findMaxIntSimple(values);
        assertEquals("Max Int should be 17", 17, maxIntSimple);
    }

    @Test
    public void testFindMaxInt() {
        int[] values = new int[] {1,3,17,2,11};
        int maxInt = FindMaxInt.findMaxInt(values);
        assertEquals("Max Int should be 17", 17, maxInt);
    }

    @Test
    public void testFindMaxNegative() {
        int[] values = new int[] {};
        int maxInt = FindMaxInt.findMaxInt(values);
        assertEquals("Max Int should be 0", Integer.MIN_VALUE, maxInt);
    }

}
