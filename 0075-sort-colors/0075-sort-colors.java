class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int left=0;
        int mid = 0;
        int high = nums.length-1;

        while(mid<=high) {
            if(nums[mid] == 0) {
                int temp = nums[left];
                nums[left]  = nums[mid];
                nums[mid] = temp;

                left++;
                mid++;
            }
            else if(nums[mid] == 1) {
                mid++;
            }

            else {
                int swap = nums[high];
                nums[high] = nums[mid];
                nums[mid] = swap;

                high--;
            }
        }
    }
}