/**
 * Node Swap
 */
public ListNode nodeSwap(ListNode head) {
    ListNode tmp = new ListNode(0);
    tmp.next = head;

    ListNode prev = tmp;
    while(prev.next != null && prev.next.next != null) {
        ListNode first = prev.next;
        ListNode second = prev.next.next;
        // prev -> first -> second -> x

        first.next = second.next;
        second.next = first;
        prev.next = second;
        // prev -> second -> first -> x

        prev = first;

    }
    return tmp.next;
 }

/**
 * Shift Linked List
 */

 public ListNode shiftLinkedList(ListNode head, int k) {
    ListNode listTail = head;
    int listLen = 1;
    while(listTail.next != null) {
        listTail = listTail.next;
        listLen++;
    }

    int shift = Math.abs(k) % listLength;
    if(shift == 0) return head;
    int newTailPosition = k > 0 ? listLen - shift : shift;
    ListNode newTail = head;
    for(int i = 1; i < newTailPosition; i++)
        newTail = newTail.next;
    
    ListNode newHead = newTail.next;
    newTail.next = null;
    listTail.next = head;
    return newHead;
 }