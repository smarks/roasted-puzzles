package com.origamisoftware.puzzles.logmerge.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Utils {
    private static SortedMap<Date, String> dateStringSortedMap = new ConcurrentSkipListMap<>();

    /**
     * Single exit point for program.
     *
     * @param exitCode          negative number denote abnormal termination. positive numbers
     *                          denote normal execution.
     * @param diagnosticMessage a helpful message to the user
     */
    public static void exit(int exitCode, String diagnosticMessage) {
        System.out.println(diagnosticMessage);
        if (exitCode < 0) {
            System.out.println("Abnormal Program Termination.");
        } else {
            System.out.println("Normal Program Termination.");
        }
        System.exit(exitCode);
    }

    /**
     * This method will look in the given directory and all subdirectories
     * for files that end with .log (case sensitive) and put them in a list
     * which is then returned to the call.
     * <p>
     * If an exception is encountered, this method calls exit(-1, with a hopefully helpful error message)
     *
     * @param inputDirectory the directory to log for log files.
     * @return a list of log files to parse
     */
    public static List<Path> getLogFiles(Path inputDirectory) {
        List<Path> fileNames = new ArrayList<>();
        // use try with resources
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(inputDirectory)) {
            for (Path path : directoryStream) {

                if (isLogFile(path)) {
                    fileNames.add(path);
                }
            }
        } catch (IOException e) {
            exit(-1, "Could not read log directory: " + inputDirectory + " " + e.getMessage());
        }

        return fileNames;
    }

    /**
     * Test to see if the given path ends with .log
     *
     * @param path the complete path
     * @return true if the path ends with .log or false otherwise.
     */
    private static boolean isLogFile(Path path) {
        return path.toString().endsWith(".log");
    }

    public static Date parseDate(String string) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        Date timeStamp = null;
        int index = string.indexOf(",");
        if (index != -1) {
            String timeStampStr = string.substring(0, index);
            try {
                timeStamp = dateFormat.parse(timeStampStr);
            } catch (ParseException e) {
                exit(-1, "Could not parse date: " + timeStampStr + " " + e.getMessage());
            }
        } else {
            throwIfNoParsableDate(string);
        }

        if (timeStamp == null) {
            throwIfNoParsableDate(string);
        }

        return timeStamp;
    }

    private static void throwIfNoParsableDate(String string) {
        throw new IllegalArgumentException(string + " has not parsable date");
    }

}
