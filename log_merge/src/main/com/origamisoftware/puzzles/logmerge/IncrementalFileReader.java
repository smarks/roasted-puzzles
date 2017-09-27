package com.origamisoftware.puzzles.logmerge;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class IncrementalFileReader {

    private BufferedReader bufferedReader;
    private Path path;

    /**
     * Make a new IncrementalFileReader
     *
     * @param path
     */
    IncrementalFileReader(Path path) {
        this.path = path;
    }

    /**
     * Get the buffered read for the given path if we haven't already.
     *
     * @return
     * @throws IOException
     */
    private BufferedReader getBufferedReader() throws IOException {
        if (bufferedReader == null) {
            bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        }
        return bufferedReader;
    }

    String readNextLine() throws IOException {
        return getBufferedReader().readLine();
    }


}
