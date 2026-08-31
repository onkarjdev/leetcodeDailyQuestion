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
        // A list with fewer than 3 nodes cannot have any critical points
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 2; // 1-based index for `curr`

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if current node is a local maxima or minima
            boolean isLocalMaxima = curr.val > prev.val && curr.val > next.val;
            boolean isLocalMinima = curr.val < prev.val && curr.val < next.val;

            if (isLocalMaxima || isLocalMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                prevCriticalIndex = currentIndex;
            }

            // Move to next node
            prev = curr;
            curr = next;
            currentIndex++;
        }

        // If fewer than two critical points were found
        if (prevCriticalIndex == -1 || firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}