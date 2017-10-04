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

package com.orgamisoftware.puzzles.grid;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for GridGame
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class GridGameTest {

    private void verifyErrorText() {
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                String log = systemOutRule.getLog();
                assertEquals("Please provide a single argument to this program, the path to the data file.\n", log);
            }
        });
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testInvalidArgumentsNoArgs() {
        exit.expectSystemExitWithStatus(-1);
        verifyErrorText();
        GridGame.main(new String[]{});
    }

    @Test
    public void testInvalidArgumentsTooManyArgs() {
        exit.expectSystemExitWithStatus(-1);
        verifyErrorText();
        GridGame.main(new String[]{"foo", "fum"});
    }

    @Test
    public void testInvalidArgumentsBadFile() {
        exit.expectSystemExitWithStatus(-1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                String log = systemOutRule.getLog();
                assertEquals("foo is not a valid file. Please provide a single argument to this program, the path to the data file.\n", log);
            }
        });
        GridGame.main(new String[]{"foo"});
    }

    @Test
    public void testBasicGame() throws Exception {
        Path inputFile = createGameDataFile();
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                String log = systemOutRule.getLog();
                assertEquals("Step: 0\n" +
                        "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n" +
                        "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n" +
                        "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n" +
                        "\n" +
                        "Step: 2,3\n" +
                        "[1]\t[1]\t[1]\t[1]\t[0]\t[0]\t[0]\t\n" +
                        "[2]\t[2]\t[2]\t[1]\t[0]\t[0]\t[0]\t\n" +
                        "[3]\t[3]\t[3]\t[2]\t[1]\t[1]\t[1]\t\n" +
                        "\n" +
                        "Step: 3,4\n" +
                        "[1]\t[1]\t[1]\t[1]\t[0]\t[0]\t[0]\t\n" +
                        "[2]\t[2]\t[2]\t[1]\t[0]\t[0]\t[0]\t\n" +
                        "[3]\t[3]\t[3]\t[2]\t[1]\t[1]\t[1]\t\n" +
                        "\n" +
                        "Step: 1,7\n" +
                        "[1]\t[1]\t[1]\t[1]\t[0]\t[0]\t[0]\t\n" +
                        "[2]\t[2]\t[2]\t[1]\t[0]\t[0]\t[0]\t\n" +
                        "[3]\t[3]\t[3]\t[2]\t[1]\t[1]\t[1]\t\n" +
                        "\n" +
                        "The value: 3 appears 3 times\n", log);
            }
        });
        GridGame.main(new String[]{inputFile.toString()});
    }


    private Path createGameDataFile() throws IOException {
        Path tempFile = Files.createTempFile("tempfiles", ".tmp");
        List<String> lines = Arrays.asList("3", "2 3", "3 4", "1 7");
        Files.write(tempFile, lines, Charset.defaultCharset(), StandardOpenOption.WRITE);
        return tempFile;
    }
}