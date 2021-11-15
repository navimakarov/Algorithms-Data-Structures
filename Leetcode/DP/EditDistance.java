public class EditDistance {
    static int editDist(String str1, String str2, int m, int n){
        if (m == 0)
            return n;

        if (n == 0)
            return m;
  
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return editDist(str1, str2, m - 1, n - 1);
  
        return 1 + min(editDist(str1, str2, m, n - 1),    // Insert
                       editDist(str1, str2, m - 1, n),    // Remove
                       editDist(str1, str2, m - 1, n - 1) // Replace
                   );
    }

    static int editDistDP(String str1, String str2, int m, int n){
        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is
                // to insert all characters of second string
                if (i == 0)
                    dp[i][j] = j; // Min. operations = j
  
                // If second string is empty, only option is
                // to remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i
  
                // If last characters are same, ignore last
                // char and recur for remaining string
                else if (str1.charAt(i - 1)
                         == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
  
                // If the last character is different,
                // consider all possibilities and find the
                // minimum
                else
                    dp[i][j] = 1 + min(dp[i][j - 1],      // Insert
                                       dp[i - 1][j],      // Remove
                                       dp[i - 1][j - 1]); // Replace
            }
        }
  
        return dp[m][n];
    }
}