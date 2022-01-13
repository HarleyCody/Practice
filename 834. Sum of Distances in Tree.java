//Best Solution: Ecapsulate to Node class, formula is improved to res[idx] = root.dist + n - 2 * root.count 
class Solution {
    class Node{
        int val;
        List<Node> nei;
        int count;//num of children
        int dist;
        public Node(int n){
            val = n;
            nei = new ArrayList<>();
            count = 0;
        }
    }
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if(n == 1) return new int[]{0};
        Node[] graph = new Node[n];
        for(int[] e : edges){
            int a = e[0];
            int b = e[1];
            if(graph[a] == null) graph[a] = new Node(a);
            if(graph[b] == null) graph[b] = new Node(b);
            graph[a].nei.add(graph[b]);
            graph[b].nei.add(graph[a]);
        }
        Node root = graph[0];//select any node as root
        calcRoot(root, 0, null);
        int[] res = new int[n];
        helper(root, res, n, -1, null);
        return res;
    }
    
    private void calcRoot(Node root, int dep, Node parent){
        int dist = dep;
        int count = 1;
        for(Node n : root.nei){
            if(n == parent) continue;
            calcRoot(n, dep + 1, root);
            dist += n.dist;
            count += n.count;
        }
        root.count = count;
        root.dist = dist;
    }
    
    private void helper(Node root, int[] res, int n, int dist, Node parent){
        int idx = root.val;
        int d = 0;
        if(dist == -1) d = root.dist;
        else{
            d = dist + n - 2*root.count;
        }
        res[idx] = d;
        for(Node node : root.nei){
            if(node == parent) continue;
            helper(node, res, n, d, root);
        }
    }
}

/*My Solution: get number of children for current node
               get the sum of distance from current node to its parent
               ans[i] == sum of distance from i to its child;
               
               root will be sum of it is child
               then add distance of nodes other than it is children. ans[i] += total - nodeOfNodes[i] + ans[parent] - toParentVal[i]; 
 */              
class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] ans = new int[n];
        int[] toParentVal = new int[n];
        List<Integer>[] child = new ArrayList[n];
        
        for(int[] edge : edges){
            int p = edge[0];
            int c = edge[1];
            if(child[p] == null){
                child[p] = new ArrayList();
            }
            child[p].add(c);
            if(child[c] == null){
                child[c] = new ArrayList();
            }
            child[c].add(p);
        }
        int[] numOfNodes= new int[n];
        getChildSum(0, -1, numOfNodes, ans, toParentVal, child);
        addSum(n, 0, -1, numOfNodes, ans, toParentVal, child);
        
        return ans;
    }
    
    private int[] getChildSum(int root, int parent, int[] numOfNodes, int[] ans, int[] toParentVal, List<Integer>[] children){
        if(children[root] == null){
            numOfNodes[root] = 1;
            toParentVal[root] = 1;
            return new int[]{1, 0};
        }
        int non = 0;
        int sum = 0;
        for(int child : children[root]){
            if(child == parent) continue;
            int[] info = getChildSum(child, root, numOfNodes, ans, toParentVal, children);
            non += info[0];
            sum += info[1];
        }
        sum += non;
        ++non;
        
        numOfNodes[root] = non;
        ans[root] = sum;
        toParentVal[root] = sum + non;
        return new int[]{non, sum};
    }
    
    private void addSum(int total, int root, int parent, int[] numOfNodes, int[] ans, int[] toParentVal, List<Integer>[] children){
        if(children[root] == null) return;
        for(int child : children[root]){
            if(child == parent) continue;
            ans[child] += total - numOfNodes[child] + ans[root] - toParentVal[child];
            addSum(total, child, root, numOfNodes, ans, toParentVal, children);
        }
    }
}
