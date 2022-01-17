//My Solution: DFS
class Solution{
	public List<List<Integer>> levelOrder(Node root){
	List<List<Integer>> ans = new ArrayList();
	collectNodes(root, ans, 0);
	return ans;
}

    private void collectNodes(Node root, List<List<Integer>> ans, int depth){
	    if(root == null) return;
	    if(ans.size() <= depth){
		    ans.add(new ArrayList());
        }
        ans.get(depth).add(root.val);
        for(Node child : root.children){
	        collectNodes(child, ans, depth + 1);
        }
    }
}

//My Solution:BFS
class Solution{
	public List<List<Integer>> levelOrder(Node root){
		if(root == null) return new ArrayList();
	    List<List<Integer>> ans = new LinkedList();
        LinkedList<Integer> kids = new LinkedList();
        kids.offer(root.val);
    	LinkedList<Node> q = new LinkedList();
    	q.offer(root);
    	while(!q.isEmpty()){
            ans.add(kids);
        	int size = q.size();
        	kids = new LinkedList();
        	while(size-- > 0){
        	    Node cur = q.poll();
        	    for(Node kid : cur.children){
	        	    if(kid == null) continue;
                    q.offer(kid);
                kids.offer(kid.val);
                }
            }
        }
        return ans;
    }
}
