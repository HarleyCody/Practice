//Best Solution: As the value is distinct only root values = sum of all nodes - sum of all children nodes and find node by val
class Solution {
    public Node findRoot(List<Node> tree) {
        int nodeValSum = 0;
        
        for (Node curr : tree) {
            nodeValSum += curr.val;
            for (Node child : curr.children) {
                nodeValSum -= child.val;
            }
        }
        for (Node curr : tree) {
            if (curr.val == nodeValSum) {
                return curr;
            }
        }
        return null;
    }
}
//My Solution: find all children and filter out the root
class Solution {
    public Node findRoot(List<Node> tree) {
        if(tree == null) return null;
        Set<Node> children = new HashSet();
        for(Node node : tree){
            if(children.contains(node)) continue;
            addChildren(children, node);
        }
        
        for(Node node : tree){
            if(children.contains(node)) continue;
            return node;
        }
        return null;
    }
    
    private void addChildren(Set<Node> children, Node node){
        if(node == null) return;
        for(Node child : node.children){
            if(!children.add(child)) continue;
            addChildren(children, child);
        }
    }
}
