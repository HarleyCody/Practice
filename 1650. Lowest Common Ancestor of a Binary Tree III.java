//My Solution: Merge sort and find while merge, O(abs(pToRoot - qToRoot))
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> pParents = new HashSet();
        Set<Node> qParents = new HashSet();
        while(p != null && q != null ){
            pParents.add(p);
            qParents.add(q);
            if(pParents.contains(q)) return q;
            if(qParents.contains(p)) return p;
            p = p.parent;
            q = q.parent;
        }
        while(p != null){
            if(qParents.contains(p)) return p;
            p = p.parent;
        }
        
        while(q != null){
            if(pParents.contains(q)) return q;
            q = q.parent;
        }
        return null;
    }
}
//My Solution: Check one by one O(q to Root + p to Root) 
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> pParents = new HashSet();
        while(p != null){
            pParents.add(p);
            p = p.parent;
        }
        while(q != null){
            if(pParents.contains(q)) return q;
            q = q.parent;
        }
        return null;
    }
}
