____________________________________________________Best Solution(recursion)___________________________________________________
class Solution {
    // recursion, a node can only be left(min, root) or right(root, max)
    // rootIdx is global because it should change with recursion, after settle down a node, next node can be upper level, so when it goes back, it still check next node.
    int rootIdx = 0;
    public boolean verifyPreorder(int[] preorder) {
        if(preorder.length == 0)
            return true;

        return helper(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);

    }

    public boolean helper(int[] preorder, int start, int end)
    {
        if(rootIdx == preorder.length)
            return true;

        if(preorder[rootIdx] < start || preorder[rootIdx] > end)
        {
            return false;
        }

        int rootVal = preorder[rootIdx];
        rootIdx++;

        boolean b1 = helper(preorder,start,rootVal);
        boolean b2 = helper(preorder,rootVal,end);

        return b1||b2;
    }
}
______________________________________________________My Solution (recursion)___________________________________________________
class Solution {
    // left descending, right ascending, and every element is in range (min, max) 
    // left root:find following first element < curRoot
    // right root: find following first element > curRoot. check every element is in the range.
    
    public boolean verifyPreorder(int[] preorder) {
        return validate(preorder, 0, preorder.length - 1, null, null);
    }
    private boolean validate(int[]preorder, int root, int end, Integer min, Integer max){
        if(root == end) return true;
        int l = 0, r = 0;
        boolean fl = false, fr = false;
        for(int i = root; i <= end; i++){
            if((min != null && preorder[i] < min) || (max != null && preorder[i] > max)){
                return false;
            }
            if(!fl && preorder[i] < preorder[root]){
                l = i;
                fl = true;
            }
            if(!fr && preorder[i] > preorder[root]){
                r = i;
                fr = true;
            }
        }
        if(r != 0 && r < l) return false;
        int endl = r == 0 ? end : r - 1;
        fl = l == 0 ? true : validate(preorder, l, endl, min, preorder[root]);
        fr = r == 0 ? true : validate(preorder, r, end, preorder[root], max);
        return fl && fr;
    }
}
