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
