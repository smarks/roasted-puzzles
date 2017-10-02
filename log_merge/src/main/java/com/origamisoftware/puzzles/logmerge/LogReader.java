package com.origamisoftware.puzzles.logmerge;

import java.io.IOException;
import java.util.Date;

import static com.origamisoftware.puzzles.logmerge.Utils.parseDate;

public class LogReader implements Comparable<LogReader> {

    private IncrementalFileReader input;
    private String currentLine;
    private Date date;

    public LogReader(IncrementalFileReader input) {
        this.input = input;
    }

    public String getCurrentLine() throws IOException {
        if (currentLine == null) {
            currentLine = input.readNextLine();
        }
        return currentLine;
    }

    public void incrementLine() throws IOException {
            currentLine = input.readNextLine();
     }

    public Date getDate() {
        date = parseDate(currentLine);
        return date;
    }

    @Override
    public int compareTo(LogReader o) {
        return this.date.compareTo(o.getDate());
    }
}
