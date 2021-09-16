/**
 * Cycle
 */
public boolean hasCycle(ListNode head) {
    if(head == null || head.next == null)
        return false;
    ListNode fast = head;
    ListNode slow = head;
    while(fast != null && fast.next != null) {
        if(fast == slow)
            return true;
        fast = fast.next.next;
        slow = slow.next;
    }
    return false;
}

/**
 * Middle node
 */
public ListNode middleNode(ListNode head) {
    ListNode fast = head, slow = head;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}

/**
 * Remove Duplicates
 */
public ListNode removeDuplicates(ListNode linkedList) {
    ListNode current = linkedList;
		while(current != null){
			while(current.next != null && current.value == current.next.value)
			    current.next = current.next.next;
			current = current.next;
		}
    return linkedList;
}

/**
 * Remove kth element from end of LinkedList
 */

 public void removeKthFromEnd(ListNode head, int k) {
    int counter = 1;
    ListNode first = head, second = head;
    while(counter <= k) {
        fast = fast.next;
        counter++;
    }
    if(second == null) {
        head.value = head.next.value;
        head.next = head.next.next;
    }
    else {
        while(second.next != null) {
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;
    } 
 }