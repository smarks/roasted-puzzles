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

/**
 * A couple different ways to find the largest int in a list of ints.
 * <p>
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class FindMaxInt {

    /**
     * Find largest in the list by sorting it and returning the last element.
     * <p>
     * The Big O for this method will be whatever the Big O is for the
     * sort method being used.  In this case it is O(n log(n)) (see src code for sort)
     *
     * @param values the array values to lists
     * @return the largest int in the list.
     */
    public static int findMaxIntSimple(int[] values) {
        Arrays.sort(values);
        return values[values.length - 1];
    }

    /**
     * Find largest in the list by looking at each element
     * <p>
     * Big O is O(1)
     * <p>
     * If the list is empty Integer.MIN_VALUE is returned.
     * (Arguably an exception could also be thrown in this case).
     *
     * @param values the array values to lists
     * @return the largest int in the list.
     */
    public static int findMaxInt(int[] values) {
        int maxInt = Integer.MIN_VALUE;
        for (int currentValue : values) {
            if (currentValue > maxInt) {
                maxInt = currentValue;
            }
        }
        return maxInt;
    }

}
