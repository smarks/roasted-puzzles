package com.origamisoftware.puzzles.logmerge;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * Single exit point for program.
     *
     * @param exitCode          negative number denote abnormal termination. positive numbers
     *                          denote normal execution.
     * @param diagnosticMessage a helpful message to the user
     */
     static void exit(int exitCode, String diagnosticMessage) {
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
    static List<Path> getLogFiles(Path inputDirectory) {
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

}
