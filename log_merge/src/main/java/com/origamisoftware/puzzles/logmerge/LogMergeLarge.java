package com.origamisoftware.puzzles.logmerge;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

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

            logs.forEach(path -> logReaders.add(new LogReader(new IncrementalFileReader(path))));
            FileWriter writer = new FileWriter(outputFilePath.toString());

            while (moreToRead(logReaders)) {
                String line = getNextLine(logReaders);
                writer.write(line, 0, line.length());
                System.out.println(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean moreToRead(List<LogReader> logReaders) {
        final boolean[] hasMoreToRead = {false};
        logReaders.forEach(new Consumer<LogReader>() {
            @Override
            public void accept(LogReader logReader) {
                if (hasMoreToRead[0] == false) {
                    if (logReader.hasNext()) {
                        hasMoreToRead[0] = true;
                    }
                }
            }
        });
        return hasMoreToRead[0];
    }

    private static String getNextLine(List<LogReader> logReaders) throws IOException {

        List<LogLine> logLines = new ArrayList<>(logReaders.size());

        logReaders.forEach(new Consumer<LogReader>() {
            @Override
            public void accept(LogReader logReader) {
                if (logReader.hasNext()) {
                    try {
                        logLines.add(logReader.next());
                    } catch (IOException e) {
                        throw new RuntimeException("Could not read line: " + e.getMessage());
                    }
                }
            }
        });

        logLines.sort(new Comparator<LogLine>() {
            @Override
            public int compare(LogLine o1, LogLine o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return logLines.get(0).getLine();

    }

}
