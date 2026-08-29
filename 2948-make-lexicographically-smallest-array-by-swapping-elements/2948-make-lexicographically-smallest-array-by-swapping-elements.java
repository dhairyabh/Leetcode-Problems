import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Pair elements with their original indices and sort by value
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        // Process connected components
        while (i < n) {
            int j = i;
            // Extend component as long as consecutive sorted elements differ by <= limit
            while (j + 1 < n && paired[j + 1][0] - paired[j][0] <= limit) {
                j++;
            }

            // Collect indices corresponding to this component and sort them
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                indices.add(paired[k][1]);
            }
            Collections.sort(indices);

            // Assign sorted values to sorted indices
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = paired[i + k][0];
            }

            i = j + 1;
        }

        return result;
    }
}