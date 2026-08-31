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
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;

        int firstCp = -1;
        int prevCp = -1;
        int minDist = Integer.MAX_VALUE;

        while (curr.next != null) {
            boolean isCritical = (curr.val > prev.val && curr.val > curr.next.val) ||
                                 (curr.val < prev.val && curr.val < curr.next.val);

            if (isCritical) {
                if (firstCp == -1) {
                    firstCp = index;
                } else {
                    minDist = Math.min(minDist, index - prevCp);
                }
                prevCp = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (firstCp == prevCp) {
            return new int[]{-1, -1};
        }

        int maxDist = prevCp - firstCp;
        return new int[]{minDist, maxDist};
    }
}