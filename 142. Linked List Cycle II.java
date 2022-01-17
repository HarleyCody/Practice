//My Solution: Detect the cycle first, set the slow back to head and the node that fast and slow meet is the anwser
public class Solution{
	public ListNode detectCycle(ListNode head){
		ListNode slowNode = head;
		ListNode fastNode = head;
		while(fastNode != null && fastNode.next != null){
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
			if(slowNode == fastNode){
				break;
			}
		}
		if(fastNode == null || fastNode.next == null){
			return null;
		}
		slowNode = head;
		while(slowNode != fastNode){
			slowNode = slowNode.next;
			fastNode = fastNode.next;
		}
		return fastNode;
	}
}
