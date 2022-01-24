//My Solution: Union find to detect cycle, if there is cycle, then it is possible answer, update answser while detect every edge
class Solution{
	int[] parent;
	int[] rank;
	public int[] findRedundantConnection(int[][] edges){
        	parent = new int[1001];
	rank = new int[1001];
		for(int i = 0; i < 1001; ++i){
	parent[i] = i;
}

	int[] ans  = edges[0];
	for(int[] edge : edges){
	if(!union(edge[0], edge[1])){
		ans = edge;
}
}

return ans;
}

private boolean union(int x, int y){
	int px = find(x);
	int py = find(y);
	if(px == py) return false;
	if(rank[px] < rank[py]){
	parent[px] = py;
}else if(rank[py] < rank[px]){
	parent[py] = px;
}else{
	parent[px] = py;
	++rank[px];
}
return true;
}

private int find(int x){
	if(parent[x] == x) return x;
	return parent[x] = find(parent[x]);
}
}
