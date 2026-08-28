import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Validate odd character counts for palindrome feasibility
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        // Try matching target's prefix of length L in the first half
        // Iterate prefix length L from halfLen down to 0
        for (int len = halfLen; len >= 0; len--) {
            int[] remFreq = halfFreq.clone();
            boolean possible = true;
            StringBuilder prefix = new StringBuilder();

            for (int i = 0; i < len; i++) {
                char c = target.charAt(i);
                if (remFreq[c - 'a'] > 0) {
                    remFreq[c - 'a']--;
                    prefix.append(c);
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            // Case 1: Matching all halfLen characters
            if (len == halfLen) {
                String fullPal = buildPalindrome(prefix.toString(), midChar);
                if (fullPal.compareTo(target) > 0) {
                    return fullPal;
                }
                continue;
            }

            // Case 2: Placing a strictly larger character at position 'len'
            char targetChar = target.charAt(len);
            for (int c = targetChar - 'a' + 1; c < 26; c++) {
                if (remFreq[c] > 0) {
                    StringBuilder firstHalf = new StringBuilder(prefix);
                    firstHalf.append((char) ('a' + c));
                    
                    int[] tempFreq = remFreq.clone();
                    tempFreq[c]--;

                    // Fill remaining positions in left half greedily with smallest chars
                    for (int j = 0; j < 26; j++) {
                        while (tempFreq[j] > 0) {
                            firstHalf.append((char) ('a' + j));
                            tempFreq[j]--;
                        }
                    }

                    String fullPal = buildPalindrome(firstHalf.toString(), midChar);
                    if (fullPal.compareTo(target) > 0) {
                        return fullPal;
                    }
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String firstHalf, char midChar) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (midChar != 0) {
            sb.append(midChar);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}