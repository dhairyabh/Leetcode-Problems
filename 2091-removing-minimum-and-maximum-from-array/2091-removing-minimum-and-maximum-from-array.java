class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Scenario 1: Remove both from front
        int removeBothFront = right + 1;

        // Scenario 2: Remove both from back
        int removeBothBack = n - left;

        // Scenario 3: Remove left from front, right from back
        int removeSplit = (left + 1) + (n - right);

        return Math.min(removeBothFront, Math.min(removeBothBack, removeSplit));
    }
}