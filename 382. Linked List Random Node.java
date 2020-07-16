________________________________________________________________________________________Improved Solution__________________________________________________________________________
class Solution {
    // initialize random once and use forver
    List<ListNode> recorder = new ArrayList();
    Random rand = new Random();
    int s;
    public Solution(ListNode head) {
        while(head != null){
            recorder.add(head);
            head = head.next;
        }
        s = recorder.size();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int idx = rand.nextInt(s);
        return recorder.get(idx).val;
    }
}
_________________________________________________________________________________________My Solution__________________________________________________________________________
class Solution {
    // Generate idx by random generator in Java
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    List<ListNode> recorder = new ArrayList();
    public Solution(ListNode head) {
        while(head != null){
            recorder.add(head);
            head = head.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int range = recorder.size();
        Random rand = new Random();
        int idx = rand.nextInt(range);
        return recorder.get(idx).val;
    }
}
