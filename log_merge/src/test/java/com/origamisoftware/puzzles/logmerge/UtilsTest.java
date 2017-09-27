package com.origamisoftware.puzzles.logmerge;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void testExitPositive() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                assertEquals("OK, then\n" +
                        "Normal Program Termination.\n", systemOutRule.getLog());
            }
        });
        Utils.exit(0, "OK, then");
    }
    @Test
    public void testExitNegative() {
        exit.expectSystemExitWithStatus(-1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                assertEquals("Excellent\n" +
                        "Abnormal Program Termination.\n", systemOutRule.getLog());
            }
        });
        Utils.exit(-1, "Excellent");
    }


}
