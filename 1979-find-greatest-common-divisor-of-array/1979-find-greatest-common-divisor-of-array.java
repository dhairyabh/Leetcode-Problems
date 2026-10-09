class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];

        for(int num:nums) {
            if(min < num) {
                min = num;
            }
            if(max>num) {
                max = num;
            }
        }

        return gcd(min, max);
    }

    public static int gcd(int a, int b) {
        while(b!=0) {
            int temp = b;
            b = a%b;
            a = temp;
        }

        return a;
    }
}