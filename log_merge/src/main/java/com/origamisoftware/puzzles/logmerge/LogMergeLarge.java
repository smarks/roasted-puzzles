package com.origamisoftware.puzzles.logmerge;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.origamisoftware.puzzles.logmerge.Utils.exit;
import static com.origamisoftware.puzzles.logmerge.Utils.getLogFiles;

public class LogMergeLarge {

    public static void main(String[] args) {

        if (args.length != 2) {
            exit(-1, "Invalid arguments. Provide directory of log files and output file.");
        }

        Path inputDirectory = Paths.get(args[0]);
        if (!Files.isDirectory(inputDirectory)) {
            exit(-1, "Invalid arguments. " + inputDirectory + "  is not a valid directory");
        }

        Path outputFilePath = Paths.get(args[1]);
        if (Files.exists(outputFilePath)) {
            exit(-1, "Invalid arguments. " + outputFilePath + "  already exists and won't be overwritten");
        }

        try {

            List<Path> logs = getLogFiles(inputDirectory);

            List<LogReader> logReaders = new ArrayList<>();

            logs.forEach(path -> logReaders.add(new LogReader(new IncrementalFileReader(path))));
            FileWriter writer = new FileWriter(outputFilePath.toString());

            String line = getNextLine(logReaders);
            writer.write(line, 0, line.length());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getNextLine(List<LogReader> logReaders) throws IOException {
        logReaders.sort(new Comparator<LogReader>() {
            @Override
            public int compare(LogReader o1, LogReader o2) {
                return o1.compareTo(o2);
            }
        });
        String currentLine = logReaders.get(0).getCurrentLine();
        logReaders.get(0).incrementLine();
        return currentLine;
    }

}
