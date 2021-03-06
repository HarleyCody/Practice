________________________________________________________________________________My Solution________________________________________________________________________________
// Merge sort
// for the tail do not need to merge one by one as it is already sorted, just connect the head of the tail
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode slow = head, fast = head;
        ListNode prev = slow;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        ListNode h1 = sortList(head);
        ListNode h2 = sortList(slow);
        
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        
        ListNode ans = null;
        if(h1.val < h2.val){
            ans = h1;
            h1 = h1.next;
        }else{
            ans = h2;
            h2 = h2.next;
        }
        
        ListNode h = ans;
        while(h1 != null && h2 != null){
            if(h1.val < h2.val){
                h.next = h1;
                h = h.next;
                h1 = h1.next;
            }else{
                h.next = h2;
                h = h.next;
                h2 = h2.next;
            }
        }
        ListNode tail = h1 == null ? h2 : h1;
        h.next = tail;
        return ans;
    }
}
_________________________________________________________________________________Best Solution________________________________________________________________________________
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)return head;//只有一个节点或者没有节点的时候返回，奇偶情况
        ListNode pre = null;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next!=null){// 用pre隔离前一半和后一半
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;//前后隔离
        ListNode l1 = sortList(head);//前半走到底
        ListNode l2 = sortList(slow);//后半走到底
        ListNode begin = new ListNode(0);
        ListNode cur = begin;
        while(l1 != null && l2 != null){//前后合并
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }
        if(l1!=null) cur.next = l1;
        if(l2!= null) cur.next = l2;
        return begin.next;//返回前后合并
    }
}//

//归并排序在每个时刻，每一个子问题的答案状态是一样的
/*                                                  9
                                                   /  \
                                                (1,4) (5,9)
                                                 /      \
                                        (1,2)(3,4)     (5,6)(7,9)
                                        / \   / \        / \   / \
                                        1 2   3 4      5   6 7  (8,9)
                                        | |   | |      |   | |    / \
                                        null  nuLL    null null   8  9
顺序为， 最底层8，9排序完成；
        次底层，12，34，56，789，排序完成；
        再上一层：1234，56789排序完成；
        最高层：123456789排序完成；
