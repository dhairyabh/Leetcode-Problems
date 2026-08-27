/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int count = 0;
    public int distributeCoins(TreeNode root) {
        distribute(root);
        return count;
    }
    public int distribute(TreeNode root) {
        if(root == null) return 0;
        int lp = distribute(root.left);
        int rp = distribute(root.right);
        count = count + Math.abs(lp) + Math.abs(rp);
        return lp + rp + root.val - 1;
    }

    class Pair {
        int count=0;
        int requirement = 0;
    }
}