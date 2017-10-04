package com.orgamisoftware.puzzles.grid;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Collection of general purpose utilities use by the GameGrid code
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class GridGameUtils {

    private static int maximum = Integer.MIN_VALUE;

    /**
     * Given an array of Strings whose values look like this:
     * <p>
     * { "2 4", "3 4", "1,5}
     * <p>
     * That is, an integer value separated by an integer value,
     * put the first integer value in the provided rows array
     * and the second integer value in the columns array.
     *
     * @param rows    - stores each first int value from the array of strings
     * @param columns stores each second int value from the array of strings
     * @param input   an array of strings which contain two ints separated by a space.
     */
    private static void getData(int[] rows, int[] columns, String[] input) {
        for (int index = 0; index < input.length; index++) {
            String[] split = input[index].split(" ");
            rows[index] = Integer.parseInt(split[0]);
            columns[index] = Integer.parseInt(split[1]);
        }
    }

    /**
     * Determine how many times the largest number appears.
     *
     * @param input an array of String which each string is a A B value.
     * @return the highest number of times the largest number appears.
     */
    static int countX(String[] input) {

        // read data into arrays
        int[] rows = new int[input.length];
        int[] columns = new int[input.length];
        getData(rows, columns, input);

        // determine how big our grid has to be and create it
        int startingRow = getTheBiggestNumber(rows);
        int maxColumns = getTheBiggestNumber(columns);
        int[][] grid = new int[startingRow][maxColumns];

        System.out.println("Step: 0");

        printGrid(grid);

        // fill in the matrix
        List<int[][]> steps = populateGrid(rows, columns, startingRow, grid);
        for (int index = 0; index < steps.size(); index++) {
            System.out.println("Step: " + rows[index] + "," + columns[index]);
            printGrid(steps.get(index));
        }

        int numberOfAppearances = countAppearances(maximum, grid);

        System.out.println("The value: " + maximum + " appears " + numberOfAppearances + " times");

        return numberOfAppearances;
    }

    /**
     * @param countUsages see how many times this value appears the grid.
     * @param grid        the matrix to traverse.
     * @return an int value - the number of times the value 'countUsages' appears in the data set.
     */
    private static int countAppearances(int countUsages, int[][] grid) {

        final int[] total = new int[1];
        IntStream stream = Arrays.stream(grid).flatMapToInt(Arrays::stream);
        stream.forEach(value -> {
            if (value == countUsages) {
                total[0] = total[0] + 1;
            }
        });
        return total[0];
    }

    /**
     * Fill in the grid by iterating of rows and column data like this:
     * <p>
     * rows[index] column[index] is one "step"
     * <p>
     * if rows[index] = 2 and columns[index] = 3
     * add one to each cell in the 2 x 3 grid.
     *
     * @param rows    each element contains a number of rows to populate.
     * @param columns each element contains a number of columns to populate.
     * @param maxRows the total number for rows (the largest int value in rows[])
     * @param grid    the matrix to populate
     */
    private static List<int[][]> populateGrid(int[] rows, int[] columns, int maxRows, int[][] grid) {
        List<int[][]> steps = new ArrayList<>(rows.length);

        for (int index = 0; index < rows.length; index++) {
            step(rows[index], columns[index], maxRows, grid);
            steps.add(grid.clone());

        }
        return steps;
    }

    private static void step(int row, int column, int maxRows, int[][] grid) {
        for (int rowIndex = maxRows - 1, rowCount = row; rowCount > 0; rowIndex--, rowCount--) {

            for (int columnIndex = 0; columnIndex < column; columnIndex++) {
                int value = grid[rowIndex][columnIndex];
                // by wrapping when we write to the matrix we can keep track of the frequency of the largest number.
                setEntry(grid, rowIndex, columnIndex, value + 1);
            }
        }
    }

    /**
     * Rather than directory writing to the grid, use this wrapper method
     * which keeps track of the largest number and the frequency it appears.
     *
     * @param matrix
     * @param row
     * @param column
     * @param value
     */
    private static void setEntry(int[][] matrix, int row, int column, int value) {
        matrix[row][column] = value;
        maximum = Math.max(value, maximum);
    }

    /**
     * Print each element in the grid.
     *
     * @param grid the two dimensional array to print
     */
    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            // print row
            for (int i : row) {
                System.out.print("[" + i + "]");
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Get the largest number in the array.
     * <p>
     * This method can used to determine the actual grid size.
     *
     * @param array array of ints
     * @return examine the array and return it's largest value.
     */
    private static int getTheBiggestNumber(int[] array) {
        int[] clone = array.clone();
        Arrays.sort(clone);
        return clone[array.length - 1];
    }

    /**
     * Given an input stream which contains data in the following format:
     * <p>
     * number of steps
     * pair of ints
     * pair of ints
     * <p>
     * e.g.
     * <p>
     * 3
     * 2 3
     * 3 4
     * 1 7
     * <p>
     * return the pairs of ints as an array of strings.
     * NOTE: it reads the first line, which is should be the total number of pairs that follow
     * and then discards it. It is not one of the strings returned.
     *
     * @param inputStream grid data
     * @return a string array of size `number of steps' where each element is a pair on ints separated by a space.
     */
    static String[] readData(InputStream inputStream) {
        Scanner in = new Scanner(inputStream);
        int arraySize = Integer.parseInt(in.nextLine());
        String[] array = new String[arraySize];
        String arrayElement;
        for (int index = 0; index < arraySize; index++) {
            arrayElement = in.nextLine();
            array[index] = arrayElement;
        }
        return array;
    }


}
