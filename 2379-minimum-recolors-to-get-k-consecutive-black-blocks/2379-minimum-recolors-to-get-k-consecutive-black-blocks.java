class Solution {
    public int minimumRecolors(String blocks, int k) {

        int white = 0;

        // First window
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                white++;
            }
        }

        int ans = white;

        // Slide the window
        for (int i = k; i < blocks.length(); i++) {

            // New character entering the window
            if (blocks.charAt(i) == 'W') {
                white++;
            }

            // Character leaving the window
            if (blocks.charAt(i - k) == 'W') {
                white--;
            }

            ans = Math.min(ans, white);
        }

        return ans;
    }
}