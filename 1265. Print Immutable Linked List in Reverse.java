______________________________________________________________________________My Solution recursion______________________________________________________________________________
class Solution {
    // same as preorder traverse, reach to buttom first and then print
    public void printLinkedListInReverse(ImmutableListNode head) {
        if(head == null){
            return;
        }
        if(head.getNext() != null){
            printLinkedListInReverse(head.getNext());
        }
        head.printValue();
    }
}
______________________________________________________________________________My Solution 2 use stack______________________________________________________________________________
class Solution {
    public void printLinkedListInReverse(ImmutableListNode head) {
        Stack<ImmutableListNode> sta = new Stack();
        while(head != null){
            sta.push(head);
            head = head.getNext();
        }
        while(!sta.isEmpty()){
            sta.pop().printValue();
        }
    }
}
