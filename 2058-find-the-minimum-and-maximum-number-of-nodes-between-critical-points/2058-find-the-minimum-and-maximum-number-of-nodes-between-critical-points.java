/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // A critical point requires at least 3 nodes (prev, curr, next)
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1; // 0-indexed position of curr

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if curr is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > next.val;
            boolean isMinima = curr.val < prev.val && curr.val < next.val;

            if (isMaxima || isMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = index;
                } else {
                    // Update minimum distance between adjacent critical points
                    minDistance = Math.min(minDistance, index - prevCriticalIndex);
                }
                prevCriticalIndex = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Return [-1, -1] if fewer than 2 critical points were found
        if (firstCriticalIndex == -1 || prevCriticalIndex == firstCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}