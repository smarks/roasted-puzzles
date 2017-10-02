package com.origamisoftware.puzzles.logmerge;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
            try {
                Files.delete(outputFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //exit(-1, "Invalid arguments. " + outputFilePath + "  already exists and won't be overwritten");
        }

        try {

            List<Path> logs = getLogFiles(inputDirectory);

            List<LogReader> logReaders = new ArrayList<>();

            logs.forEach(path -> {
                try {
                    logReaders.add(new LogReader(new BufferedReader(new FileReader(path.toString()))));
                } catch (FileNotFoundException e) {
                    exit(-1, "Could not find log file: " + path.toString() + " " + e.getMessage());
                }
            });

            FileWriter writer = new FileWriter(outputFilePath.toString());

            while (moreToRead(logReaders)) {
                String line = getNextLine(logReaders);
                writer.write(line, 0, line.length());
                System.out.println(line);
            }
            writer.close();
        } catch (IOException e) {
            exit(-1, "Could write log file: " + outputFilePath.toString() + " " + e.getMessage());
        }

    }

    private static boolean moreToRead(List<LogReader> logReaders) {
        final boolean[] hasMoreToRead = {false};
        for (LogReader logReader : logReaders) {
            if (hasMoreToRead[0] == false) {
                if (logReader.hasNext()) {
                    hasMoreToRead[0] = true;
                }
            }
        }
        return hasMoreToRead[0];
    }

    private static String getNextLine(List<LogReader> logReaders) throws IOException {

        List<LogLine> logLines = new ArrayList<>(logReaders.size());

        logReaders.forEach(logReader -> {
            if (logReader.hasNext()) {
                try {
                    logLines.add(logReader.next());
                } catch (IOException e) {
                    throw new RuntimeException("Could not read line: " + e.getMessage());
                }
            }
        });

        logLines.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return logLines.get(0).getLine();

    }

}
