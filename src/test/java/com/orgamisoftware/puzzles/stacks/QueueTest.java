package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
        assertEquals("Should be " +strings[2] + strings[2], strings[2], array[0]);
        assertEquals("Should be " +strings[3] + strings[3], strings[3], array[1]);
        assertEquals("Should be " + newValue + newValue, newValue, array[2]);

    }
}
