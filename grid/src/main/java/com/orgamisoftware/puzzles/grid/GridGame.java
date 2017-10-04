package com.orgamisoftware.puzzles.grid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The purposes of this program is to determine how many times
 * the largest number appears in a matrix of integers.
 * <p>
 * This program will populate the matrix following these rows:
 * <p>
 * Start with an  infinite 2-dimensional grid with the bottom left cell referenced as (1, 1).
 * where all the cells contain a value of zero initially.
 * <p>
 * e.g here's your grid (infinite rows of 0 omitted)
 * <p>
 * 0
 * <p>
 * Populate the matrix in series of N steps where N is the number of
 * pairs of integers values you are given.
 * <p>
 * In each step, you are given two integers a and b.
 * <p>
 * a is the number of rows to
 * b is the number of columns
 * <p>
 * increment the the value in each cell of these rows and columns.
 * <p>
 * For example if the first pair of values is 2,3 the grid would look like this:
 * <p>
 * 1 1 1
 * 1 1 1
 * <p>
 * after the first step.
 * <p>
 * If the next pair of value was 3,4 the grid would look like this:
 * <p>
 * 1 1 1 1
 * 2 2 2 1
 * 2 2 2 1
 * <p>
 * after the second step.
 * <p>
 * If the next pair was 1,7 the grid would look like:
 * <p>
 * 1 1 1 1 0 0 0
 * 2 2 2 1 0 0 0
 * 3 3 3 2 1 1 1
 * <p>
 * Once all the pairs of ints have been applied (all the steps are complete)
 * examine the grid to determine how many times the largest value is present.
 * <p>
 * In this example the largest value is 3 and it appears 3 times.
 * <p>
 * Get the grid data:
 * <p>
 * This program will read in a data file in form of
 * <p>
 * 3
 * 2 3
 * 3 4
 * 1 7
 * <p>
 * The first line 3 is the total number of rows (or steps).
 * Each proceeding line of data contains the pair of int values.
 * That is, the number of rows (a from above example) and columns (b)
 * <p>
 * 3    <-- total number of rows
 * 2 3  <-- rows, columns to increment
 * 3 4  <-- rows, columns to increment
 * 1 7  <-- rows, columns to increment
 * <p>
 * This data is stored in array of Strings.
 * The array of String is placed into grid following the rules above.
 * After each step the grid is displayed in the console.
 * After the last step how many times the largest int is present is displayed.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class GridGame {


    /**
     * Provide a single argument a path to the data file.
     * The data file must in the form of:
     * <p>
     * number_of_steps
     * row column
     * row column
     * <p>
     * e.g.
     * <p>
     * 3
     * 2 3
     * 3 4
     * 1 7
     *
     * @param args path to data file.
     */
    public static void main(String[] args) {

        String incorrectArgs = "Please provide a single argument to this program, the path to the data file.";

        if (args.length != 1) {
            abnormalTermination(incorrectArgs);
        }

        String path = args[0];
        try {
            String[] gridData = GridGameUtils.readData(new FileInputStream(path));
            GridGameUtils.countX(gridData);
        } catch (FileNotFoundException e) {
            abnormalTermination(path + " is not a valid file. " + incorrectArgs);
        }
    }

    /**
     * Exit program with a -1 status code. Write the string to System.out
     *
     * @param message the message to print
     */
    private static void abnormalTermination(String message) {
        System.out.println(message);
        System.exit(-1);
    }
}