package com.origamisoftware.puzzles.logmerge;

import java.io.IOException;
import java.util.Date;

import static com.origamisoftware.puzzles.logmerge.Utils.parseDate;

public class LogReader {

    private IncrementalFileReader input;

    public LogReader(IncrementalFileReader input) {
        this.input = input;
    }

    public boolean hasNext() {
        try {
            return input.ready();
        } catch (IOException e) {
            throw new RuntimeException("Could not determine if there was more to read: " + e.getMessage());
        }
    }

    public LogLine next() throws IOException{
        if (hasNext()) {
        String line = input.readNextLine();
        Date date = parseDate(line);
        return new LogLine(line,date);
    } else {
            throw new RuntimeException("No more data to read, call hasNext() before call next() to avoid this error in the future");
        }
}
}
