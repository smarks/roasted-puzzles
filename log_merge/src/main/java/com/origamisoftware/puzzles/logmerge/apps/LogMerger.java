/*
 *  Copyright  (c)  2017.  Spencer Marks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.origamisoftware.puzzles.logmerge.apps;

import com.origamisoftware.puzzles.logmerge.model.LogLine;
import com.origamisoftware.puzzles.logmerge.model.LogReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.origamisoftware.puzzles.logmerge.util.Utils.exit;
import static com.origamisoftware.puzzles.logmerge.util.Utils.getLogFiles;

/**
 * A more complex / object oriented  solution to the problem of merging log files described in the ReadMe.md file.
 * This solution DOES  reply on the individual logs being sorted by date, since it only reads one line of each log
 * file at a time and writes output one line at a time, it can process a "a very large number" (tm) of log files
 * without running out of resources.
 * <p>
 * Here is the basic algorithm:
 * <p>
 * 0. read a line from all files
 * 1. sort lines by date
 * 2. write earliest entry
 * 3. read line from file whose line was just written or remove it from list of files if no data remains
 * 4. while there is more data in any of the files go to step 1
 * <p>
 * <p>
 * This program only has basic exception handling.
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class LogMerger {

    /**
     *
     * This program takes two arguments. The directory where one or more log files are located and output file.
     *
     * The log files must be in this format:
     * <p>
     * 2017-04-26 12:00:04,799  DEBUG - Lorem ipsum dolor sit amet, consectetur adipiscing elit
     * <p>
     * This program will collect each line and write them sorted by date into a single file.
     * <p>
     * Note: a log file is defined a any file ending with .log
     * <p>
     * <p>
     * This method will terminate the program in a controlled way if an <CODE>ParseException</CODE> or an <CODE>IOException</CODE>
     * is encountered.
     *
     * @param args an array of strings. Size must be 2. The first arg must be a directory. The second arg is the path
     *             to output file.  If the output file already exists, it won't be overwritten. Instead the program
     *             will terminate with an error message.
     */
    public static void main(String[] args) {

        // Sanity check input args
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

        } catch (Throwable e) {
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
