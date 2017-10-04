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

package com.orgamisoftware.puzzles.stacks;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Unit test to for Queue class
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class QueueTest {

    private static String[] strings = new String[]{"a", "b", "c", "d"};

    @Test
    public void testQueueBasic() {
        Queue<String> queue = new Queue<String>();
        for (String string : strings) {
            queue.add(string);
        }

        for (String string : strings) {
            assertEquals("Should be " + string, string, queue.get());
        }

    }

    @Test
    public void testQueueInsert() {
        Queue<String> queue = new Queue<String>();
        for (String string : strings) {
            queue.add(string);
        }
        String string = strings[0];
        String actual = queue.get();
        assertEquals("Should be " + string, string, actual);
        String newValue = "e";
        queue.add(newValue);
        string = strings[1];
        actual = queue.get();
        assertEquals("Should be " + string, string, actual);
        String[] array = new String[4];
        for (int count = 0; count < 3; count++) {
            array[count] = queue.get();
        }
        assertEquals("Should be " + strings[2] + strings[2], strings[2], array[0]);
        assertEquals("Should be " + strings[3] + strings[3], strings[3], array[1]);
        assertEquals("Should be " + newValue + newValue, newValue, array[2]);

    }
}
