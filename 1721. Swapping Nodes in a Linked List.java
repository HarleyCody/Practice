//Best Solution: record kth and last kth with O(n). after the kth is found, main tain the window size of left and right to continue to get the lastKth.
class Solution { // READ THE PROBLEM NUNCE
    public ListNode swapNodes(ListNode head, int k) {
        // Setup dummy nodes
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        while ( i < k ) { // create the offsets
            dummy = dummy.next;
            fast = fast.next;
            i++;
        }
        
        while (fast != null) { // iterate until end of list
            slow = slow.next;
            fast = fast.next;
        }
        // swap values accordingly
        int tmpVal = dummy.val;
        dummy.val = slow.val;
        slow.val = tmpVal;
        
        return head;
    }
}

//My Solution: Record the pos of kth and lastKth. and swap the value
class Solution{
	public ListNode swapNodes(ListNode head, int k){
	int len = 0;
	ListNode curNode = head;
	while(curNode != null){
		++len;
		curNode = curNode.next;
}

ListNode kthNode = null;
ListNode rKthNode = null;
curNode = head;

int curIdx = 0;
while(curNode != null){
	if(curIdx == k - 1){
	kthNode = curNode;
}
if(curIdx == len - k){
	rKthNode = curNode;
}
curNode = curNode.next;
++curIdx;
}
if(kthNode == null) return head;
int tmp = kthNode.val;
	kthNode.val = rKthNode.val;
	rKthNode.val = tmp;

	return head;
}
}
