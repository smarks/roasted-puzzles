package com.origamisoftware.puzzles.logmerge.apps;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.origamisoftware.puzzles.logmerge.util.Utils.exit;
import static com.origamisoftware.puzzles.logmerge.util.Utils.getLogFiles;


/**
 * Simple solution to the problem of merging log files described in the ReadMe.md file.
 * This solution does not reply on the individual logs being sorted by date, but since it
 * reads all the data into a hashmap which is stored in memory it can run out of memory
 * if the amount of data is very large.
 * <p>
 * This program only has basic exception handling.
 */
public class LogMergeBasic {


    /**
     * Read each line of the file.
     * <p>
     * This method will terminate the program (in a controllled way) if an <CODE>ParseException</CODE> or an <CODE>IOException</CODE>
     * is encountered.
     *
     * @param log                 the file to read
     * @param dateStringSortedMap the data structure to store the lines of the log file in.
     */
    private static SortedMap<Date, String> parseLog(Path log, SortedMap<Date, String> dateStringSortedMap) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");//format of date and time you have

        /* Read each line of the file using a stream which can handle large files
         * parse the date of the line
         * put the line in the map sorted by date.
         */
        try (Stream<String> stream = Files.lines(log)) {

            stream.forEach(new Consumer<String>() {
                @Override
                public void accept(String line) {
                    // the date part of the string stops at ','
                    int index = line.indexOf(",");
                    // make sure ',' was found
                    if (index != -1) {
                        // grab the date part of the string
                        String timeStampStr = line.substring(0, index);
                        try {
                            Date timeStamp = dateFormat.parse(timeStampStr);
                            dateStringSortedMap.put(timeStamp, line);
                        } catch (ParseException e) {
                            exit(-1, "Could not parse date: " + timeStampStr + " " + e.getMessage());
                        }
                    }
                }
            });

        } catch (IOException e) {
            exit(-1, "Could not parse file: " + log + " " + e.getMessage());

        }
        return dateStringSortedMap;
    }

    /**
     * Given one more log files in the format of:
     * <p>
     * 2017-04-26 12:00:04,799  DEBUG - Lorem ipsum dolor sit amet, consectetur adipiscing elit
     * <p>
     * This program will collect each line and write them sorted by date into a single file.
     * <p>
     * This program takes two arguments. The directory where one or more log files are located.
     * Note: a log file is defined a any file ending with .log
     * <p>
     * The second out argument in the output
     * <p>
     * This method will terminate the program in a controllled way if an <CODE>ParseException</CODE> or an <CODE>IOException</CODE>
     * is encountered.
     *
     * @param args an array of strings. Size must be 2. The first arg must be a directory. The second arg is the path
     *             to output file.  If the output file already exists, it won't be overwritten. Instead the program
     *             will terminate with an error message.
     */
    public static void main(String[] args) {
        // sanity  check input args
        if (args.length != 2) {
            exit(-1, "Invalid arguments. Provide directory of log files and output file.");
        }

        Path inputDirectory = Paths.get(args[0]);
        if (!Files.isDirectory(inputDirectory)) {
            exit(-1, "Invalid arguments. " + inputDirectory + "  is not a valid directory");
        }

        Path outputFile = Paths.get(args[1]);
        if (Files.exists(outputFile)) {
            exit(-1, "Invalid arguments. " + outputFile + "  already exists and won't be overwritten");
        }

        List<Path> logs = getLogFiles(inputDirectory);

        SortedMap<Date, String> dateStringSortedMap = new ConcurrentSkipListMap<>();

        for (Path log : logs) {
            parseLog(log, dateStringSortedMap);
        }

        // handy try with resources
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {

            // iterate the map by date writing each line
            dateStringSortedMap.forEach(new BiConsumer<Date, String>() {
                /**
                 * Write contents to output file.
                 * If an <CODE>IOException</CODE> is encountered the program will terminate with an error message.
                 *
                 * @param date     the date of the log line  - not used
                 * @param contents a log line
                 */
                @Override
                public void accept(Date date, String contents) {
                    try {
                        writer.write(contents + "\n");
                    } catch (IOException e) {
                        exit(-1, "Could not write: + " + contents + " to " + outputFile + " " + e.getMessage());
                    }
                }
            });

        } catch (IOException e) {
            exit(-1, "Could not write to: " + outputFile + " " + e.getMessage());
        }

        exit(0, "All done checkout: " + outputFile);
    }
}
