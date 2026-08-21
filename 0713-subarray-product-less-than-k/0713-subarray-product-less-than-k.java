class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int ans = 0;
        int p=1;
        int right=0;
        while(right<nums.length) {
            if(k<=1) return 0;
            p *= nums[right];
            while(p>=k) {
                p/=nums[left];
                left++;
            }

            ans += right-left+1;
            right++;

        }

        return ans;
    }
}