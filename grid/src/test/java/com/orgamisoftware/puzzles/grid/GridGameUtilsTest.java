package com.orgamisoftware.puzzles.grid;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertEquals;

public class GridGameUtilsTest {

    private static final String INPUT_DATA =
            "3\n" +
                    "3\n" +
                    "2 3\n" +
                    "3 4\n" +
                    "1 7\n" +
                    "1 7\n";

    @Test
    public void testReadData() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream(INPUT_DATA.getBytes(StandardCharsets.UTF_8.name()));
        String[] grid = GridGameUtils.readData(inputStream);
        assertEquals("grid is correct", "3", grid[0]);
        assertEquals("grid is correct", "2 3", grid[1]);
        assertEquals("grid is correct", "3 4", grid[2]);
        assertEquals("grid is correct", "1 7", grid[3]);


    }
}
