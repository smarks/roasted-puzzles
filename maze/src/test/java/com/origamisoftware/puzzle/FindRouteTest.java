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

package com.origamisoftware.puzzle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemErrRule;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for the FindRoute application
 *
 * @author <A href="mailto:smarks@origamisoftware.com">Spencer A  Marks</A>
 */
public class FindRouteTest {

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void verifyNoArguments() {
        exit.expectSystemExitWithStatus(-1);
        FindRouteApplication.main(new String[]{});
        assertEquals(" -map VAL : Specify the full path to an xml that describes the map\n" +
                " -scenario VAL : Specify the full path to text file that describes a search\n" +
                "                 scenario", systemErrRule.getLog());
    }

    @Test
    public void verifyIncorrectArguments() {
        exit.expectSystemExitWithStatus(-1);
        FindRouteApplication.main(new String[]{"-map", "foo", "-snap", "caller"});
        assertEquals(" -map VAL : Specify the full path to an xml that describes the map\n" +
                " -scenario VAL : Specify the full path to text file that describes a search\n" +
                "                 scenario", systemErrRule.getLog());
    }

    @Test(expected = IllegalStateException.class)
    public void verifyCorrectArgumentsBadPaths() {
        FindRouteApplication.main(new String[]{"-map", "map.xml", "-scenario", "caller"});
    }

    @Test()
    public void verifyCorrectArgumentsGoodPaths() {
        FindRouteApplication.main(new String[]{"-map", "./data/map.xml", "-scenario", "./data/scenario.txt"});
    }
}
