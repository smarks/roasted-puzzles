package com.orgamisoftware.puzzles.lists;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Unit test for QuickSort
 * <p>
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class QuickSortTest {

    @Test
    public void basicTest() {
        int[] values = new int[]{1, 3, 17, 2, 11};
        int[] sorted = SimpleQuickSort.quickSort(values);
        Arrays.sort(values);
        assertEquals("Sorted array should be 17,11,3,2,1", values, sorted);
    }
}
