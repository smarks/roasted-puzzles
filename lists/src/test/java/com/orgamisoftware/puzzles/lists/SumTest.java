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
        int[] values = new int[]{1, 2, 3};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 6", 6, sum);
    }

    @Test
    public void testRecursiveSum2() {
        int[] values = new int[]{1, 2, 3, 10};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 16", 16, sum);

    }

    @Test
    public void testRecursiveSumNegative() {
        int[] values = new int[]{};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 0", 0, sum);

    }

    @Test
    public void testRecursiveSumNegative2() {
        int[] values = new int[]{0};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 0", 0, sum);

    }

    @Test
    public void testRecursiveSum3() {
        int[] values = new int[]{0, 0, 1};
        int sum = RecursiveSum.sum(values);
        assertEquals("Return value should be 1", 1, sum);

    }
}
