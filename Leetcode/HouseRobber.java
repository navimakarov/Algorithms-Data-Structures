public class HouseRobber {
    public int rob(int[] nums) {
        Map<Integer, Integer> cache = new HashMap<>();
        return Math.max(robbing(nums, 0, cache), robbing(nums, 1, cache));
    }
    
    private int robbing(int[] nums, int idx, Map<Integer, Integer> cache) {
        if(idx >= nums.length)
            return 0;
        
        if(cache.containsKey(idx))
            return cache.get(idx);
        
        int res = nums[idx] + robbing(nums, idx+2, cache);
        int i = 3;
        while(idx+i < nums.length) {
            res = Math.max(res, nums[idx]+robbing(nums, idx+i, cache));
            i++;
        }
        cache.put(idx, res);
        return res;
    }
}