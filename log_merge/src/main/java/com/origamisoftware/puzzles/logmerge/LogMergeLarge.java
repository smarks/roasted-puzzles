package com.origamisoftware.puzzles.logmerge;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        Path outputFile = Paths.get(args[1]);
        if (Files.exists(outputFile)) {
            exit(-1, "Invalid arguments. " + outputFile + "  already exists and won't be overwritten");
        }

        List<Path> logs = getLogFiles(inputDirectory);

        List<IncrementalFileReader> logFiles = new ArrayList<>();

        logs.forEach(path -> logFiles.add(new IncrementalFileReader(path)));


    }

}
