_______________________________________________________________My(Best) Solution_________________________________________________________

class Solution {
    //find by recursion ans[0] record smaller subtree ans[1] record larger tree
    // V == root.val root is in ans[0], root.right is in ans[1];
    // V > root.val => split point is in right tree ans[1] will be fixed;
    //                 root.right should connect smaller value ie ans[0], ans[1] is root;
    // V < root.val => split point is in left tree; ans[0] will be fixed;
    //                  root.left should connect larger value ie ans[1], ans[0] is root;
    public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode[] ans = new TreeNode[2];
        if(root == null){
            return ans;
        }
        if(root.val == V){
            ans[0] = root;
            ans[1] = root.right;
            root.right = null;
        }else if(root.val > V){
            ans[1] = root;
            if(root.left != null){
                TreeNode[] next = splitBST(root.left, V);
                root.left = next[1];
                ans[0] = next[0];
            }
        }else if(root.val < V){
            ans[0] = root;
            if(root.right != null){
                TreeNode[] next = splitBST(root.right, V);
                root.right = next[0];
                ans[1] = next[1];
            }
        }
        return ans;
    }
}
