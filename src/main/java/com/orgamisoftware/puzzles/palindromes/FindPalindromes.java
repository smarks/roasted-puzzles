package com.orgamisoftware.puzzles.palindromes;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.System.exit;

/**
 * Five Dwarves ( Gimli Fili Ilif Ilmig and Mark) met at the Prancing Pony and played a word game to determine which combinations
 * of their names resulted in a palindrome. It turned out to be harder problem than they realized so they commissioned this
 * program.
 * <p>
 * Given an arbitrary number of strings this program will examine all the possible combinations and print a list of those
 * combinations that are palindromes.
 *
 * @author Spencer Marks (smarks@origamisoftware.com)
 *         <p>
 *         Jul 2017
 */
public class FindPalindromes {

    /**
     * Return a list of palindromes for the given input.
     *
     * @param input one more string values
     * @return a list of palindromes from provided input. It could be empty.
     */
    @VisibleForTesting
    static List<String> getPalindromes(Set<String> input) {

        List<String> returnValue = new ArrayList<>();
        Set<Set<String>> subsets = getSubsetsGuava(input);
        subsets.forEach(strings -> {
            List<String> candidatePalindromes = permute(strings);
            for (String candidate : candidatePalindromes) {
                if (!candidate.isEmpty()) {
                    if (isPalindrome(candidate)) {
                        returnValue.add(candidate);
                    }
                }
            }
        });

        return returnValue;
    }

    /**
     * Entry point for recursive algorithm that returns all the possible permutations for the given array.
     * <p>
     * e.g. input  a b c
     * <p>
     * output:
     * <p>
     * abc
     * acb
     * bac
     * bca
     * cba
     * cab
     *
     * @param input the data set to analyze
     * @return a list where each value is a permutation
     */
    @VisibleForTesting
    static List<String> permute(Set<String> input) {
        List<String> results = new ArrayList<>();
        String[] strings = input.toArray(new String[input.size()]);
        permute(strings, 0, results);
        return results;
    }

    /**
     * Recursive entry point for permute
     *
     * @param input   the data set to analyze
     * @param index   the current index into the data set
     * @param results a place to store the results.
     */
    private static void permute(String[] input, int index, List<String> results) {

        // check to see if we are at the last element if so nothing left to permute
        if (index >= input.length - 1) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < input.length - 1; i++) {
                stringBuilder.append(input[i]);
            }

            if (input.length > 0) {
                stringBuilder.append(input[input.length - 1]);
            }

            results.add(stringBuilder.toString());
            return;
        }
        // otherwise for each index in the sub array  recurse
        for (int i = index; i < input.length; i++) {
            //Swap the elements at indices index and i
            String t = input[index];
            input[index] = input[i];
            input[i] = t;

            //Recurse on the sub array arr[index+1...end]
            permute(input, index + 1, results);

            //Swap the elements back
            t = input[index];
            input[index] = input[i];
            input[i] = t;
        }
    }


    /**
     * This method find all the subsets of the given set using Google's
     * most excellent Guava library.
     * <p>
     * Notes:
     * Duplicate elements are ignored.
     * "" counts as a element
     *
     * @param dataSet an array of Strings.
     * @return a set of sets of strings that contains all combinations and permutations of the dataSet provided.
     */
    static Set<Set<String>> getSubsetsGuava(Set<String> dataSet) {
        return Sets.powerSet(dataSet);
    }

    /**
     * Given a string return true if it is a Palindrome false otherwise
     * <p>
     * A Palindrome is defined as:
     * <p>
     * A palindrome is a word, phrase, number, or other sequence of
     * characters which reads the same backward as forward
     * <p>
     * Any a sequence of just one character is a palindrome.
     * An empty string is also a palindrome, since it "reads" the same forward and backward.
     * <p>
     * Character case (e.g. Y y) is not used determining if the letters match.
     * <p>
     * A null value provided as argument value is treated as an programming error.
     *
     * @param string the set of characters to examine.
     * @return true if it is a Palindrome false otherwise
     */
    @VisibleForTesting
    static boolean isPalindrome(String string) {

        // test for exceptional cases that don't require additional processing.
        int length = string.length();
        if (length == 0 || length == 1) {
            return true;
        }

        // don't let spaces between words interfere
        string = string.replace(" ", "");
        char[] chars = string.toCharArray();
        length = string.length();

        int startIndex = 0;
        int endIndex = length - 1;

        while (startIndex != endIndex && endIndex > 0 && startIndex < length - 1) {
            if (Character.toLowerCase(chars[startIndex]) != Character.toLowerCase(chars[endIndex])) {
                return false;
            }

            startIndex = startIndex + 1;
            endIndex = endIndex - 1;
        }
        return true;
    }

    /**
     * Print program overview and instructions.
     */
    private static void usage() {
        System.out.println("This program checks the input arguments to see if any combination of them are palindromes");
        System.out.println("A palindrome is defined as:");
        System.out.println("A sequence of characters which reads the same backward as forward");
        System.out.println("");
        System.out.println("Any sequence of just one character is a palindrome.");
        System.out.println("An empty string is also a palindrome, since it 'reads' the same forward and backward.");
        System.out.println("");
        System.out.println("Character case is not used in determining if the letters match.(e.g. Y is the same as y)");
        System.out.println("");
        System.out.println("Run this program with one or more arguments to check if they are palindromes");
        System.out.println("");
        System.out.println("e.g. java -cp . FindPalindromes Gimli Fili Ilif Ilmig Mark");
    }

    /**
     * Main entry point to FindPalindromes application.
     * This program checks the input arguments to see if any combination of them are palindromes
     * Supply one or more arguments
     *
     * @param args one or more values.
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments specified.");
            usage();
            exit(-1);
        }

        List<String> palindromes = getPalindromes(Sets.newHashSet(args));
        if (!palindromes.isEmpty()) {

            System.out.print("The following combinations of: ' ");
            for (String arg : args) {
                System.out.print(arg + " ");
            }
            System.out.println("' are palindromes: ");
            palindromes.forEach(System.out::println);

        } else {
            System.out.println("No palindromes found.");
        }

        exit(0);
    }
}


