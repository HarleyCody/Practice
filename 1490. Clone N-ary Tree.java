//My Solution: DFS clone current node and add clone version of children
class Solution {
    public Node cloneTree(Node root) {
        if(root == null) return null;
        Node ans = new Node(root.val);
        for(Node child : root.children){
            ans.children.add(cloneTree(child));
        }
        return ans;
    }
}
