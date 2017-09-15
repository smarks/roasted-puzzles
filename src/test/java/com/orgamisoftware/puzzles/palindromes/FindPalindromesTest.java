package com.orgamisoftware.puzzles.palindromes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple FindPalindromes.
 */
public class FindPalindromesTest {


    private static final String realData[] = new String[]{"Gimli", "Fili", "Ilif", "Ilmig", "Mark"};
    private static final Set<String> realDataSet = new HashSet<String>(Arrays.asList(realData));
    private static final String simpleData[] = new String[]{"a", "b", "c", "d"};
    private static final Set<String> simpleDataSet = new HashSet<String>(Arrays.asList(simpleData));
    private static final String noPalindromesHereData[] = new String[]{"Mark","Spencer"};

    private static String[] knownPalindromes = new String[] {
            "",
            " ",
            "  ",
            "T",
            "z",
            "Mam",
            "Mam",
            "Anna",
            "Civic",
            "Kayak",
            "Level",
            "Madam",
            "Mom",
            "Noon",
            "Racecar",
            "Radar",
            "Redder",
            "Refer",
            "Repaper",
            "Rotator",
            "Rotor",
            "Sagas",
            "Solos",
            "Stats",
            "Tenet",
            "I did, did I",
            "Wow"
    };

    private static String[] notPalindromes = new String[]{
            "BBox", "Bozo", "foobar", "most any other words", "I did, did not I"
    };

    @Test
    public void testVerifyPalindromesPositive() {
        for (String palindrome : knownPalindromes) {
            assertTrue(palindrome + " is a palindrome", FindPalindromes.isPalindrome(palindrome));
        }
    }

    @Test
    public void testVerifyNotPalindromesPositive() {
        for (String string : notPalindromes) {
            assertFalse(string + " is not a palindrome", FindPalindromes.isPalindrome(string));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testVerifyPalindromesNullCase() {
        FindPalindromes.isPalindrome(null);
    }

    @Test
    public void testGetPossibleSubsets() {
        List<String> possibleCombinations = FindPalindromes.permute(simpleDataSet);
        assertEquals("Combination should be 24", 24, possibleCombinations.size());
    }

    @Test
    public void testGetSubsetsGuava() {
        Set<Set<String>> permutationsGuava = FindPalindromes.getSubsetsGuava(simpleDataSet);
        // Guava includes "" as  valid permutation
        assertEquals("Permutations should be 16", 16, permutationsGuava.size());
    }

    @Test
    public void testGetPalindromesGuava() {
        List<String> palindromes = FindPalindromes.getPalindromes(realDataSet);
        assertEquals("The number of palindromes should be 12", 12, palindromes.size());
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testInvalidArgument() {
        exit.expectSystemExitWithStatus(-1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                assertEquals("No arguments specified.\n" +
                        "This program checks the input arguments to see if any combination of them are palindromes\n" +
                        "A palindrome is defined as:\n" +
                        "A sequence of characters which reads the same backward as forward\n" +
                        "\n" +
                        "Any sequence of just one character is a palindrome.\n" +
                        "An empty string is also a palindrome, since it 'reads' the same forward and backward.\n" +
                        "\n" +
                        "Character case is not used in determining if the letters match.(e.g. Y is the same as y)\n" +
                        "\n" +
                        "Run this program with one or more arguments to check if they are palindromes\n" +
                        "\n" +
                        "e.g. java -cp . FindPalindromes Gimli Fili Ilif Ilmig Mark\n", systemOutRule.getLog());
            }
        });
        FindPalindromes.main(new String[0]);
    }

    @Test
    public void testFindPalindromes() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                assertEquals("The following combinations of: ' Gimli Fili Ilif Ilmig Mark ' are palindromes: \n" +
                        "FiliIlif\n" +
                        "IlifFili\n" +
                        "GimliIlmig\n" +
                        "IlmigGimli\n" +
                        "GimliFiliIlifIlmig\n" +
                        "GimliIlifFiliIlmig\n" +
                        "FiliGimliIlmigIlif\n" +
                        "FiliIlmigGimliIlif\n" +
                        "IlifGimliIlmigFili\n" +
                        "IlifIlmigGimliFili\n" +
                        "IlmigFiliIlifGimli\n" +
                        "IlmigIlifFiliGimli" +
                        "\n", systemOutRule.getLog());
            }
        });
        FindPalindromes.main(realData);
    }

    @Test
    public void testNoPalindromesFound() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                assertEquals("No palindromes found.\n",systemOutRule.getLog());
            }
        });
        FindPalindromes.main(noPalindromesHereData);
    }
}



