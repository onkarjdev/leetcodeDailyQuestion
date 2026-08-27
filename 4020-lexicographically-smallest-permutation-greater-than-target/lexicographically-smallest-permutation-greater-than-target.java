class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] sCounts = new int[26];
        for (char c : s.toCharArray()) {
            sCounts[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] counts = sCounts.clone();
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (counts[charIdx] > 0) {
                    counts[charIdx]--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            int targetChar = target.charAt(i) - 'a';
            int chooseChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (counts[c] > 0) {
                    chooseChar = c;
                    break;
                }
            }

            if (chooseChar != -1) {
                counts[chooseChar]--;
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + chooseChar));

                for (int c = 0; c < 26; c++) {
                    while (counts[c] > 0) {
                        sb.append((char) ('a' + c));
                        counts[c]--;
                    }
                }

                return sb.toString();
            }
        }

        return "";
    }
}