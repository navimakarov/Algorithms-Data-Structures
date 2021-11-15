public class LongestCommonSubstring {
    public String getLongestCommonSubstring(String str1, String str2){
        int m = str1.length();
        int n = str2.length();

        int max = 0;

        int[][] dp = new int[m][n];
        Arrays.fill(dp, 0);
        int endIndex=-1;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(str1.charAt(i) == str2.charAt(j)){

                    // If first row or column
                    if(i==0 || j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j] = dp[i-1][j-1]+1;
                    }

                    if(dp[i][j] > max) {
                        max = dp[i][j];
                        endIndex=i;
                    }
                }

            }
        }
        return str1.substring(endIndex-max+1,endIndex+1);
    }
}