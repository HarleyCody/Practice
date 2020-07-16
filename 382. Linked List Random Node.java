________________________________________________________________________________________Follow Ups Solution____________________________________________________________
public class Solution {
    // without extra space, unknow size
    // go through list
    // at every node if it returns a random number that is idx of node, use node as ans node
    ListNode head;
    Random random;
    
    public Solution(ListNode h) {
        head = h;       
        random = new Random();        
    }
    
    public int getRandom() {
        
        ListNode c = head;
        int r = c.val;
        for(int i=1;c.next != null;i++){
            
            c = c.next;
            if(random.nextInt(i + 1) == i) r = c.val;                        
        }
        
        return r;
    }
}
________________________________________________________________________________________Follow Ups Solution____________________________________________________________
// without extra space;
class Solution {
    // Generate idx by random generator in Java
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    Random rand = new Random();
    ListNode h;
    int s = 0;
    public Solution(ListNode head) {
        h = head;
        while(head != null){
            ++s;
            head = head.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode cur = h;
        int steps = rand.nextInt(s);
        while(steps-- > 0){
            cur = cur.next;
        }
        return cur.val;
    }
}
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
