package com.origamisoftware.puzzles.logmerge;

import java.io.IOException;
import java.util.Date;

import static com.origamisoftware.puzzles.logmerge.Utils.parseDate;

public class LogReader implements Comparable<LogReader>{

    private IncrementalFileReader input;
    private String currentLine;
    private Date date;

    public LogReader(IncrementalFileReader input) {
        this.input = input;
    }

    private String getCurrentLine() throws IOException {
        currentLine = input.readNextLine();
        return currentLine;
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
