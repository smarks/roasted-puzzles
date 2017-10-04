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
 * An example of get the total of the int values in an array using recursion.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class RecursiveSum {

    /**
     * Sum the elements of the array using recursion.
     *
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
