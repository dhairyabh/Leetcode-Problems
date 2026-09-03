class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        
        for (int num : nums1) {
            if (num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            } else {
                minEven = Math.min(minEven, num);
            }
        }
        
        // If there are no odd numbers or no even numbers, target parity is already satisfied
        if (minOdd == Integer.MAX_VALUE || minEven == Integer.MAX_VALUE) {
            return true;
        }
        
        // 1. Check if all-even target is possible:
        // Every odd element must be strictly greater than minOdd.
        // Since minOdd cannot be converted to even, an all-even array is only possible 
        // if there are NO odd numbers at all (handled above).
        boolean canBeAllEven = false; 

        // 2. Check if all-odd target is possible:
        // Every even element must be strictly greater than minOdd.
        boolean canBeAllOdd = (minEven > minOdd);
        
        return canBeAllEven || canBeAllOdd;
    }
}