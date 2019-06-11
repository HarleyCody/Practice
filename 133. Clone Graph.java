/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
    // reocorder is a global variable, so it does not need to be a paramenter in recursion
    HashMap<Integer, Node> recorder = new HashMap();
    public Node cloneGraph(Node node) {
        return clone(node);
    }
    
    public Node clone(Node node){
        // this node has been copied, returned copied node.
        if(recorder.containsKey(node.val))return recorder.get(node.val);
        // create a new node to copy.
        Node newNode = new Node(node.val, new ArrayList<Node>());
        // update recorder.
        recorder.put(newNode.val, newNode);
        // enlarge neighbor list, in the list, should be copied node -> recursion starts.
        for(Node nodes : node.neighbors){
            newNode.neighbors.add(clone(nodes)); 
        }
        return newNode;
    }
}
