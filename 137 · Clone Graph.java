//My Solution: bfs copy
public class Solution {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> pairs = 
        new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> nodes = new LinkedList<UndirectedGraphNode>();
        nodes.offer(node);
        while(!nodes.isEmpty()){
            UndirectedGraphNode cur = nodes.poll();
            if(!pairs.containsKey(cur)){
                pairs.put(cur, new UndirectedGraphNode(cur.label));
            }
            UndirectedGraphNode newCur = pairs.get(cur);
            for(UndirectedGraphNode neighbor : cur.neighbors){
                if(!pairs.containsKey(neighbor)){
                    pairs.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    nodes.add(neighbor);
                }
                newCur.neighbors.add(pairs.get(neighbor));
            }
        }
        return pairs.get(node);
    }
}
//My Solution: use hashmap to record the node pairs for dp. and dfs to clone node one by one;
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        Map<UndirectedGraphNode, UndirectedGraphNode> dp = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return deepCopy(node, dp);
    }

    private UndirectedGraphNode deepCopy(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> dp){
        if(node == null) return null;
        if(dp.containsKey(node)) return dp.get(node);

        UndirectedGraphNode nNode = new UndirectedGraphNode(node.label);
        nNode.neighbors = new ArrayList<UndirectedGraphNode>();
        dp.put(node, nNode);
        for(UndirectedGraphNode ugNode : node.neighbors){
            nNode.neighbors.add(deepCopy(ugNode, dp));
        }
        return nNode;
    }
}
