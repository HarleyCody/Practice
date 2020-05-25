_______________________________________________________________My Solution______________________________________________________________
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
// deser and ser recursively, preorder traverse, from top to bottom
// has children, dive into children first then return
class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null){
            return null;
        }
        return serialize(root, new StringBuilder()).toString();
    }
	
    // Decodes your encoded data to tree.
    int idx = 0;
    public Node deserialize(String data) {
        if(data == null){
            Node ans = new Node(0, new ArrayList());
            ans = null;
            return ans;
        }
        String[] datum = data.split(" ");
        return deserialize(datum, 0);
    }
    
    private StringBuilder serialize(Node root, StringBuilder ans){
        ans.append(root.val);
        ans.append(' ');
        if(root.children.size() != 0){
            ans.append('(');
            ans.append(' ');
            for(Node n : root.children){
                serialize(n, ans);
            }
            ans.append(')');
            ans.append(" ");
        }
        return ans;
    }
    
    private Node deserialize(String[] data, int start){
        idx = start;
        Node ans = new Node(Integer.parseInt(data[idx++]));
        ans.children = new ArrayList<Node>();
        if(idx < data.length && data[idx].equals("(")){
            // skip '('
            ++idx;
            while(idx < data.length && !data[idx].equals(")")){
                ans.children.add(deserialize(data, idx));
            }
            // skip ')'
            ++idx;
        }
        return ans;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
