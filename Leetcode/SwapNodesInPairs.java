public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode next = head.next.next;
        ListNode newHead = head.next;
        head.next = swapPairs(next);
        newHead.next = head;
        return newHead;
    }
}