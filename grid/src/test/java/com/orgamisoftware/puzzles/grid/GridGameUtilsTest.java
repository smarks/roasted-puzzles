package com.orgamisoftware.puzzles.grid;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertEquals;


/**
 * Unit test for GridGameUtils
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class GridGameUtilsTest {

    private static final String INPUT_DATA =
            "3\n" +
                    "2 3\n" +
                    "3 4\n" +
                    "1 7\n";

    private String[] getStrings() throws Exception {
        InputStream inputStream = new ByteArrayInputStream(INPUT_DATA.getBytes(StandardCharsets.UTF_8.name()));
        return GridGameUtils.readData(inputStream);
    }

    @Test
    public void testReadData() throws Exception {
        String[] grid = getStrings();
        assertEquals("grid is correct", "2 3", grid[0]);
        assertEquals("grid is correct", "3 4", grid[1]);
        assertEquals("grid is correct", "1 7", grid[2]);
    }

    @Test
    public void testCountX() throws Exception {
        int i = GridGameUtils.countX(getStrings());
        assertEquals("The correct answer is 3", 3, i);
    }
}