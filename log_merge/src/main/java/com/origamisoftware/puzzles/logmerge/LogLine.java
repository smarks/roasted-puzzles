package com.origamisoftware.puzzles.logmerge;

import java.util.Date;

public class LogLine implements Comparable<LogLine> {

    private String line;
    private Date date;

    public LogLine(String line, Date date) {
        this.line = line;
        this.date = date;
    }

    public String getLine() {
        return line;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(LogLine o) {
        return date.compareTo(o.date);
    }
}
