import java.util.*;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int r=0;
        int [] hash = new int[256];
        Arrays.fill(hash,-1);
        int l = 0;
        int maxlen=0;
        
        while(r<n) {
            if(hash[s.charAt(r)] != -1) {
                if(hash[s.charAt(r)] >= l) {
                    l = hash[s.charAt(r)] + 1;
                }
            }
            int len = r-l+1;
            maxlen = Math.max(maxlen, len);
            hash[s.charAt(r)] = r;
            r++;
        }

        return maxlen;
    }
}