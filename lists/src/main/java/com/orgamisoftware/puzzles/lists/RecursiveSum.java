package com.orgamisoftware.puzzles.lists;

import java.util.Arrays;

/**
 * An example of get the total of the int values in an array using recursion.
 *
 *  @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class RecursiveSum {

    /**
     * Sum the elements of the array using recursion.
     * @param values the array values to lists
     * @return an int value which is total of all the ints inthe array
     */
    public static int sum(int[] values) {

        // base case
        if (values.length == 0) {
            return 0;

        } else if (values.length == 1) {
            return values[0];

        }
        // recursive case
        int[] values1 = Arrays.copyOf(values, values.length - 1);
        return values[values.length - 1] + sum(values1);
    }

}
