class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int globalSum = sum(nums, 0, nums.length-1);
        int currSum;
        for(int i = 0; i < nums.length; i++) {
            currSum = globalSum;
            if(currSum == k)
                count++;
            for(int j = nums.length-1; j > i; j--) {
                currSum -= nums[j];
                if(currSum == k)
                    count++;
            }
            globalSum -= nums[i];
        }
        return count;
    }
    
    public int sum (int[] nums, int i, int j) {
        int s = 0;
        for(int x = i; x <= j; x++) {
            s += nums[x];
        }
        return s;
    }
}