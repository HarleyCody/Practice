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
