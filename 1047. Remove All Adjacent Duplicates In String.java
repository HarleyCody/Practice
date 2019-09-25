____________________________________________________Best Solution______________________________________________________________
class Solution {
    public String removeDuplicates(String S) {
        char[] arr = S.toCharArray();
        int pivot = 0;
        // two pointers, i, pivot, i check every element, pivot: indexOf last element.
        // == poviot move to prev,(delete cur cause cur may be rewrite later);
        // != poviot move to next place, put i in new place(rewrite arr[i]);
        for (int i = 1; i < arr.length; i++) {
            //  == pivot point to last element, or null 
            if (pivot > -1 && arr[i] == arr[pivot]) {
                pivot--;
            } else {
                // 
                pivot++;
                //reset start of arr, i would be move to first. 
                arr[pivot] = arr[i];
            }
        }
        // 0: initial, pivot + 1, length;
        return new String(arr, 0, pivot + 1);
    }
}
____________________________________________________My Solution________________________________________________________________
class Solution {
    // deque store the chars, and peek last to compare with curChar. 
    // if same remove last skip curChar;
    // else offer curChar
    // use stringbuilder to append( so use deque not stack);
    public String removeDuplicates(String S) {
        Deque<Character> dq = new LinkedList();
        for(char c : S.toCharArray()){
            if(dq.isEmpty()){
                dq.offer(c);
            }else{
                char last = dq.peekLast();
                if(last != c){
                    dq.offer(c);
                }else{
                    dq.removeLast();
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        while(!dq.isEmpty()){
            ans.append(dq.pop());
        }
        return ans.toString();
    }
}
