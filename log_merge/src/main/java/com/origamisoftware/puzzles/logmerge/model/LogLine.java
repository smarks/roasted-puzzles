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
