class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            boolean isLeft = i < n / 2;
            char c = num.charAt(i);

            if (c == '?') {
                qDiff += isLeft ? 1 : -1;
            } else {
                sumDiff += isLeft ? (c - '0') : -(c - '0');
            }
        }

        // Bob wins if qDiff is even and the sum difference balances out
        return !(qDiff % 2 == 0 && sumDiff + (qDiff / 2) * 9 == 0);
    }
}