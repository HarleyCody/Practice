/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
// record node and value in map, connect with dfs, if next has been build retrieve from map otherwise continue connect and build new node
class Solution {
    HashMap<Integer, Node> recorder = new HashMap();
    public Node cloneGraph(Node node) {
        if(node == null){
            return node;
        }
        return clone(node);
    }
    
    private Node clone(Node node){
        Node cur = new Node(node.val);
        recorder.put(cur.val, cur);
        for(Node n : node.neighbors){
            if(recorder.containsKey(n.val)){
                cur.neighbors.add(recorder.get(n.val));
            }else{
                cur.neighbors.add(clone(n));
            }
        }
        
        return cur;
    }
}
