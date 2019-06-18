/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // preorder
    private void serialize(TreeNode node, StringBuilder sb) {
        // record value first then claim type of node, B has left and right, L has only left, R has only right
        sb.append(node.val);
        if(node.left != null && node.right != null) {
            sb.append('B');
            // continue append possible node;
            serialize(node.left, sb);
            serialize(node.right, sb);
        } else if(node.left != null) {
            sb.append('L'); 
            serialize(node.left, sb);
        } else if(node.right != null) {
            sb.append('R');
            serialize(node.right, sb);
        } else {
            sb.append('N');// stop when its children are both null
        }
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return null;
        StringBuilder sb = new StringBuilder();
        
        serialize(root, sb);
        return sb.toString();
    }
    
    private TreeNode deserialize(String data, int[] ind) {
        int index = ind[0];
        TreeNode node = null;
        Integer val = 0;
        //get value from string
        while(Character.isDigit(data.charAt(index)))
        val=(val * 10 + Character.getNumericValue(data.charAt(index++)));
        
        //get type of value
        char type = data.charAt(index++);
        ind[0] = index;// pos after type
        
        node=new TreeNode(val);
        if(type == 'L' || type == 'B')
        node.left = deserialize(data, ind);
        // ind will be changed with recur as it is an array.
        if(type == 'R' || type == 'B')
        node.right = deserialize(data, ind);
        
        return node;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) return null;
        
        return deserialize(data, new int[]{0});
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
