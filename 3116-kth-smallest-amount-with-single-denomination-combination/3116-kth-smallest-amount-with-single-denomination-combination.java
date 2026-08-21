import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Step 1: Duplicate multiples ko filter out kar do (Efficiency ke liye)
        Arrays.sort(coins);
        List<Integer> filtered = new ArrayList<>();
        for (int coin : coins) {
            boolean isMultiple = false;
            for (int f : filtered) {
                if (coin % f == 0) {
                    isMultiple = true;
                    break;
                }
            }
            if (!isMultiple) {
                filtered.add(coin);
            }
        }

        int[] c = filtered.stream().mapToInt(i -> i).toArray();

        // Step 2: Binary Search Range
        long low = 1;
        long high = (long) c[0] * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (countAmounts(mid, c) >= k) {
                ans = mid;
                high = mid - 1; // Aur chhota amount try karo
            } else {
                low = mid + 1; // Badha ke check karo
            }
        }

        return ans;
    }

    // Amount 'target' tak kitne unique values ban rahe hain (using Inclusion-Exclusion)
    private long countAmounts(long target, int[] coins) {
        long totalCount = 0;
        int n = coins.length;

        // Bitmasking se saare non-empty subsets iterate karenge
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    // Optimization: Agar LCM target se bada ho gaya toh calculate karna bekar h
                    if (currentLcm > target) break;
                }
            }

            // Odd subsets ko add karo, Even subsets ko subtract karo
            if (bitCount % 2 != 0) {
                totalCount += target / currentLcm;
            } else {
                totalCount -= target / currentLcm;
            }
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}