class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pref = new int[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }
        int ans = pref[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            ans = Math.max(ans, pref[i] - ans);
        }
        
        return ans;
    }
}