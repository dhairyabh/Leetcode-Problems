class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int count = 0;
        int left = 0;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }

            while (count == k) {
                String sub = s.substring(left, right + 1);
                
                if (result.isEmpty() || sub.length() < result.length() || 
                   (sub.length() == result.length() && sub.compareTo(result) < 0)) {
                    result = sub;
                }

                if (s.charAt(left) == '1') {
                    count--;
                }
                left++;
            }
        }

        return result;
    }
}