public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s.length() == 0)
            return "";
        boolean dp[][] = new boolean[s.length()][s.length()];
        int max = 1;
        int x = 0, y = 0;
        for(int i = 0; i < s.length(); i++) {
           for(int j = i; j < s.length(); j++) {
               if(i == j)
                   dp[i][j] = true;
               else if(j-i == 1) {
                   dp[i][j] = (s.charAt(i) == s.charAt(j));
                   if(dp[i][j] && j-i+1 > max) {
                       max = j-i+1;
                       x = i;
                       y = j;
                   }
               }
           }
        }
        
        for(int i = s.length()-1; i >= 0; i--) {
            for(int j = i+2; j < s.length(); j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i+1][j-1];
                if(dp[i][j] && j-i+1 > max) {
                    max = j-i+1;
                    x = i;
                    y = j;
                }
            }
        }
        return s.substring(x, y+1);
    }
}