___________________________________________________________________Best Solution_________________________________________________________
// recorder idx of array that is monoly decrease, compare val to pop and set val;
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        int len = 0;
        ListNode current = head;
        while(current != null) {
            len++;
            current = current.next;
        }
        int[] value = new int[len], result = new int[len], index = new int[len];
        int count = 0, ptr = -1;
        for(current = head; current != null; current = current.next, count++) {
            while(ptr >= 0 && current.val > value[ptr])
                result[index[ptr--]] = current.val;
            ptr++;
            index[ptr] = count;
            value[ptr] = current.val;
        }
        return result;
    }
}
___________________________________________________________________My Solution___________________________________________________________
// bind idx with val, modify the idx specifically
class Solution {
    class Node{
        int val;
        int idx;
        
        public Node(int v, int i){
            val = v;
            idx = i;
        }
    }
    public int[] nextLargerNodes(ListNode head) {
        Stack<Node> sta = new Stack();
        int[] ans = new int[10001];
        int idx = 0;
        while(head != null){
            while(!sta.isEmpty() && sta.peek().val < head.val){
                ans[sta.peek().idx] = head.val;
                sta.pop();
            }
            sta.push(new Node(head.val, idx));
            ++idx;
            head = head.next;
        }
        return Arrays.copyOfRange(ans, 0, idx);
    }
}
