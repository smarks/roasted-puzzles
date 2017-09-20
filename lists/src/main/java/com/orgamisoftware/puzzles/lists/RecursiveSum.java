package com.orgamisoftware.puzzles.lists;

import java.util.Arrays;

public class RecursiveSum {

    /**
     * Sum the elements of the array using recursion.
     * @param values the array values to lists
     * @return an int value which is total of all the ints inthe array
     */
    public static int sum(int[] values) {

        if (values.length == 0) {
            return 0;

        } else if (values.length == 1) {
            return values[0];

        }

        int[] values1 = Arrays.copyOf(values, values.length - 1);
        return values[values.length - 1] + sum(values1);
    }

}
