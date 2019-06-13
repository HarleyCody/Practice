/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        //get length of ListNode
        int len1 = count(l1);
        int len2 = count(l2);
        // move to the end of list
        ListNode h1 = move(len1, l1);
        ListNode h2 = move(len2, l2);
        // make sure l1 has longer length
        if(len1 < len2){
            ListNode tem = l1;
            l1 = l2;
            l2 = tem;
            int temp = len1;
            len1 = len2;
            len2 = temp;
        }
        //start with last node
        int newVal = (h1.val + h2.val) % 10;
        int overflow = (h1.val + h2.val) / 10;
        ListNode rlt = add(len1 - 1, len2 - 1, l1, l2, new ListNode(newVal), overflow);
        return rlt;
    }

    public ListNode add(int pos1, int pos2, ListNode l1, ListNode l2, ListNode ans, int overflow){
        // all add done, add overflow if possible
        if(pos1 < 0){
            //add ListNode overflow
            if(overflow > 0){
                ListNode newNode = new ListNode(overflow);
                newNode.next = ans;
                ans = newNode;
            }
            return ans;
        }
        // Not done yet, find reqired node (Last Node);
        ListNode h1 = move(pos1, l1);
        ListNode h2 = move(pos2, l2);
        int newVal = h1.val + h2.val + overflow;
        // update overflow for previous node
        overflow = newVal / 10;
        // keep val as digit;
        newVal %= 10;
        ListNode newNode = new ListNode(newVal);
        // connect with List
        newNode.next = ans;
        //recursivly add previous untill pos1 < 0;
        return add(--pos1, --pos2, l1, l2, newNode, overflow);
    }
    // count len
    private int count(ListNode l){
        int i = 0;
        while(l.next != null){
            l = l.next;
            ++i;
        }
        return i;
    }
    // find node in pos, if pos < 0 return node with value 0. 
    private ListNode move(int pos, ListNode l){
        if(pos < 0)return new ListNode(0);
        int i = 0;
        while(i++ < pos){
            l = l.next;
        }
        return l;
    }
}
