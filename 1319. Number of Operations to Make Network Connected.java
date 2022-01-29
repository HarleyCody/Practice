//My Solution: Union found with recording the size, for every group use one line to connect and update the available connections based on size. Use size - 1 connections for internal connections
class Solution{
	int[] parent;
	int[] size;
	public int makeConnected(int n, int[][] connections){
	parent = new int[n];
	size = new int[n];
	for(int i = 0; i < n; ++i){
	parent[i] = i;
	size[i] = 1;
}
for(int[] connection : connections){
	 union(connection[0], connection[1]);
}
int availableLines = connections.length;
int ans = 0;
for(int i = 0; i < n; ++i){
	if(size[i] != 0){
	++ans;
	availableLines -= size[i] - 1;
}
}
        --ans;
return ans > availableLines? -1 : ans;
}

private void union(int x, int y){
	int px = find(x);
	int py = find(y);
    if(px == py) return;
	parent[py] = px;
    size[px] += size[py];
	size[py] = 0;
}

private int find(int x){
	if(x == parent[x]) return x;
	return parent[x] = find(parent[x]);
}
}
