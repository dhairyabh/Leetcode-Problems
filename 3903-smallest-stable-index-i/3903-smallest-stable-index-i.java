class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] minSuffix = new int[n];
        
        // Build the suffix minimum array from right to left
        minSuffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = Math.min(nums[i], minSuffix[i + 1]);
        }
        
        // Iterate through nums while keeping track of the prefix maximum
        int maxPrefix = nums[0];
        for (int i = 0; i < n; i++) {
            maxPrefix = Math.max(maxPrefix, nums[i]);
            
            if (maxPrefix - minSuffix[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}