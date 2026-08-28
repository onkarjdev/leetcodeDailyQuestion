import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        for (int i = halfLen; i >= 0; i--) {
            int[] currentHalfCount = halfCount.clone();
            boolean possible = true;
            StringBuilder prefix = new StringBuilder();

            for (int j = 0; j < i; j++) {
                char tc = target.charAt(j);
                if (currentHalfCount[tc - 'a'] > 0) {
                    prefix.append(tc);
                    currentHalfCount[tc - 'a']--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            if (i == halfLen) {
                String candidate = makePalindrome(prefix.toString(), midChar, n);
                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
                continue;
            }

            char targetChar = target.charAt(i);
            for (int c = targetChar - 'a' + 1; c < 26; c++) {
                if (currentHalfCount[c] > 0) {
                    StringBuilder half = new StringBuilder(prefix);
                    half.append((char) ('a' + c));

                    int[] tempCount = currentHalfCount.clone();
                    tempCount[c]--;

                    for (int k = 0; k < 26; k++) {
                        while (tempCount[k] > 0) {
                            half.append((char) ('a' + k));
                            tempCount[k]--;
                        }
                    }

                    String candidate = makePalindrome(half.toString(), midChar, n);
                    if (candidate.compareTo(target) > 0) {
                        return candidate;
                    }
                }
            }
        }

        return "";
    }

    private String makePalindrome(String firstHalf, char midChar, int totalLen) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (totalLen % 2 != 0) {
            sb.append(midChar);
        }
        StringBuilder secondHalf = new StringBuilder(firstHalf).reverse();
        sb.append(secondHalf);
        return sb.toString();
    }
}