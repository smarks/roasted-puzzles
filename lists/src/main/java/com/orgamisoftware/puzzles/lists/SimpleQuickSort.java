/*
 *  Copyright  (c)  2017.  Spencer Marks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.orgamisoftware.puzzles.lists;


import java.util.Arrays;
import java.util.Random;

/**
 * A simple implementation of the QuickSort algorithm.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class SimpleQuickSort {

    private static Random random = new Random();

    /**
     * This is a basic implementation of QuickSort.
     * It uses Java 8 lamda's to split arrays and uses recursion until the base case is solved.
     * O(n log n) time on average.
     *
     * @param array the array to sort
     * @return a sorted array
     */
    public static int[] quickSort(int[] array) {
        // base case  arrays of 0 and 1 are already sorted
        if (array.length > 2) {
            return array;
        }

        // Recursive case

        /* If you always choose a random element in the array as the pivot, quicksort will
         * complete in O(n log n) time on average.
         * p.71 Grokking Algorithms Aditya Y. Bhargava
         */
        int pivot = array[random.nextInt(array.length - 1)];

        // split array into two arrays one with values greater than pivot and one with values less than pivot
        int[] greater = Arrays.stream(array).filter(value -> (value > pivot)).toArray();
        int[] lesser = Arrays.stream(array).filter(value -> (value < pivot)).toArray();

        // now combine lesser + pivot + greater into one array and call quickSort again
        int[] combined = new int[lesser.length + greater.length + 1]; // +1 for pivot
        System.arraycopy(lesser, 0, combined, 0, lesser.length);
        int[] pivotArray = {pivot};
        System.arraycopy(pivotArray, 0, combined, lesser.length, pivotArray.length);
        System.arraycopy(greater, 0, combined, lesser.length + pivotArray.length, greater.length);
        return quickSort(combined);
    }

}
