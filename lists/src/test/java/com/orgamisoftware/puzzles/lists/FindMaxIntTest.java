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

import static junit.framework.TestCase.assertEquals;

/**
 * Unit tests for FindMaxInt class
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class FindMaxIntTest {

    @Test
    public void testFindMaxIntSimple() {
        int[] values = new int[]{1, 3, 17, 2, 11};
        int maxIntSimple = FindMaxInt.findMaxIntSimple(values);
        assertEquals("Max Int should be 17", 17, maxIntSimple);
    }

    @Test
    public void testFindMaxInt() {
        int[] values = new int[]{1, 3, 17, 2, 11};
        int maxInt = FindMaxInt.findMaxInt(values);
        assertEquals("Max Int should be 17", 17, maxInt);
    }

    @Test
    public void testFindMaxNegative() {
        int[] values = new int[]{};
        int maxInt = FindMaxInt.findMaxInt(values);
        assertEquals("Max Int should be 0", Integer.MIN_VALUE, maxInt);
    }

}
