package org.example;

import java.util.ArrayList;
import java.util.List;
public class KMPStringMatcher {
    private int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public List<Integer> search(String text, String pattern) {
        List<Integer> results = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m == 0) return results;
        if (n < m) return results;

        int[] lps = computeLPS(pattern);
        int i = 0; // text index
        int j = 0; // pattern index

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                results.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        KMPStringMatcher kmp = new KMPStringMatcher();

        System.out.println("=== KMP Algorithm Test Results ===");

        // Test 1: Basic functionality
        System.out.println("Test 1: " + kmp.search("hello world", "world"));

        // Test 2: Multiple occurrences
        System.out.println("Test 2: " + kmp.search("abcabcabc", "abc"));

        // Test 3: Overlapping patterns
        System.out.println("Test 3: " + kmp.search("aaaaaa", "aa"));

        // Test 4: No match found
        System.out.println("Test 4: " + kmp.search("abcdef", "xyz"));

        // Test 5: Complex pattern
        System.out.println("Test 5: " + kmp.search("mississippi", "iss"));

        // Test 6: DNA sequence
        System.out.println("Test 6: " + kmp.search("AGCTAGCTAGCT", "AGCT"));
    }
}
