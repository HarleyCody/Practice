___________________________________________________________________________________Best Solution_______________________________________________________________________________
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        final char[] cs1 = str1.toCharArray();
        final char[] cs2 = str2.toCharArray();
        final int len1 = cs1.length, len2 = cs2.length;

        // dp 求最长公共子序列长度
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (cs1[i] == cs2[j]) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0; ) {
            char c;
            if (i < 0) {
                c = cs2[j--];
            } else if (j < 0) {
                c = cs1[i--];
            } else if (cs1[i] == cs2[j]) {
                // 公共字符
                c = cs1[i--];
                j--;
            } else if (dp[i][j + 1] == dp[i + 1][j + 1]) {
                // str1 中有str2 中没有的字符
                c = cs1[i--];
            } else {
                // dp[i + 1][j] == dp[i + 1][j + 1]
                // str2 中有str1 中没有的字符
                c = cs2[j--];
            }
            sb.append(c);
        }
        return sb.reverse().toString();
    }
}
