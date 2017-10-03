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

public class LogMergeBasic {


    private static SortedMap<Date, String> dateStringSortedMap = new ConcurrentSkipListMap<>();


    /**
     * Read each line of the file.
     *
     * @param log the file to read
     */
    private static SortedMap<Date, String> parseLog(Path log) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");//format of date and time you have

        try (Stream<String> stream = Files.lines(log)) {

            stream.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    int index = s.indexOf(",");
                    if (index != -1) {
                        String timeStampStr = s.substring(0, index);
                        try {
                            Date timeStamp = dateFormat.parse(timeStampStr);
                            dateStringSortedMap.put(timeStamp, s);
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
     *
     * @param args
     */
    public static void main(String[] args) {
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

        for (Path log : logs) {
            parseLog(log);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {

            dateStringSortedMap.forEach(new BiConsumer<Date, String>() {
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
