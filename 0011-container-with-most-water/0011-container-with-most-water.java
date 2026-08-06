class Solution {
    public int maxArea(int[] height) {
      int n = height.length; 
      int left=0;
      int maxArea=0;
      int right = n-1;
      while(left<right) {
        int h = Math.min(height[left], height[right]);
        int width = right - left;
        int Area = h * width;
        maxArea = Math.max(maxArea, Area);

        if(height[left] < height[right]) {
            left++;
        }else{
            right--;
        }
      } 
        return maxArea;
    }
}