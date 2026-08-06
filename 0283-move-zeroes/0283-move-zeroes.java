class Solution {
    public void moveZeroes(int[] nums) {
      zero(nums);
    }
    public static int[] zero(int[] arr) {
        int n = arr.length;
        int i=0;
        int j=0;
        while(j<n) {
            if(arr[j]!=0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
            j++;
        }

        return arr;
    }
}