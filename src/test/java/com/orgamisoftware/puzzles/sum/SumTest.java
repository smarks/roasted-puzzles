package com.orgamisoftware.puzzles.sum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumTest {

    @Test
    public void testRecursiveSum() {
        int[] values = new int[] {1,2,3};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 6", 6, sum);

    }
}
