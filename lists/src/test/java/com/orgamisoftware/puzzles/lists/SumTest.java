package com.orgamisoftware.puzzles.lists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Sum class
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class SumTest {

    @Test
    public void testRecursiveSum() {
        int[] values = new int[] {1,2,3};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 6", 6, sum);

    }

    @Test
    public void testRecursiveSum2() {
        int[] values = new int[] {1,2,3,10};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 16", 16, sum);

    }

    @Test
    public void testRecursiveSumNegative() {
        int[] values = new int[] {};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 0", 0, sum);

    }

    @Test
    public void testRecursiveSumNegative2() {
        int[] values = new int[] {0};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 0", 0, sum);

    }

    @Test
    public void testRecursiveSum3() {
        int[] values = new int[] {0,0,1};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 1", 1, sum);

    }
}
