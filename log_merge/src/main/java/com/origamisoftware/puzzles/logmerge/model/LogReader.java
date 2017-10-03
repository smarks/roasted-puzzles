package com.origamisoftware.puzzles.logmerge.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import static com.origamisoftware.puzzles.logmerge.util.Utils.parseDate;

public class LogReader {

    private BufferedReader input;

    public LogReader(BufferedReader input) {
        this.input = input;
    }

    public boolean hasNext() {
        try {
            boolean ready = input.ready();
            if (ready == false) {
                input.close();
            }
            return ready;
        } catch (IOException e) {
            throw new RuntimeException("Could not determine if there was more to read: " + e.getMessage());
        }
    }

    public LogLine next() throws IOException {
        if (hasNext()) {
            String line = input.readLine();
            Date date = parseDate(line);
            return new LogLine(line, date);
        } else {
            throw new RuntimeException("No more data to read, call hasNext() before call next() to avoid this error in the future");
        }
    }
}
