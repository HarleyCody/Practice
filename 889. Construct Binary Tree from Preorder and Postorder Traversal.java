//Best Solution: Find the left and right child and merget to root, use preorder traverse to match postorder number
class Solution {
    int preIndex = 0;
    int postIndex = 0;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        TreeNode curr = new TreeNode(preorder[preIndex++]);
        if(curr.val != postorder[postIndex]){
            curr.left = constructFromPrePost(preorder, postorder);
        }
        if(curr.val != postorder[postIndex]){
            curr.right = constructFromPrePost(preorder, postorder);
        }
        
        ++postIndex;
        return curr;
    }
}
//My Solution: Narrow down the range by second of preorder and last second of postorder
// second of preorder must be left child, last second of post order could be right child
// if they are same then only need to connect the left
// left = pre[1 ~ index of second last element of post order in preorder], post(0 ~ index of second element of pre order in post order]
// right = pre[index of second last element of post order in preorder ~ end], post(index of second element of pre order in post order ~ end - 1]
class Solution {
    int[] preIndice;
    int[] pstIndice;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int len = preorder.length;
        if(len == 0) return null;
        preIndice = new int[len + 1];
        pstIndice = new int[len + 1];
        for(int i = 0; i < len; ++i){
            preIndice[preorder[i]] = i;
            pstIndice[postorder[i]] = i;
        }
        
        return buildTree(preorder, 0, len - 1, postorder, 0, len - 1);
    }
    private TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] pst, int pstStart, int pstEnd){
        TreeNode root = new TreeNode(pre[preStart]);
        if(preStart == preEnd) return root;
        int preIdx = preIndice[pst[pstEnd - 1]];
        int pstIdx = pstIndice[pre[preStart + 1]];
        
        if(pst[pstEnd - 1] == pre[preStart + 1]){
            root.left = buildTree(pre, preStart + 1, preEnd, pst, pstStart, pstEnd - 1);
        }else{
            root.left = buildTree(pre, preStart + 1, preIdx - 1, pst, pstStart, pstIdx);
            root.right = buildTree(pre, preIdx, preEnd, pst, pstIdx + 1, pstEnd - 1);
        }
        
        return root;
    }
}
