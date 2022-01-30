//Best Solution: Use dfs to get the sum of prefix word. Calcualte the every child node that go through the current node
class MapSum {
    private class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int weight=0;
    }
    TrieNode root = null;
    public MapSum() {
        root=new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode curr = root;
        for(char c : key.toCharArray()){
            if(curr.children[c-'a']==null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr=curr.children[c-'a'];
        }
        curr.weight=val;
    }
    
    public int sum(String prefix) {
        TrieNode curr = root;
        for(char c : prefix.toCharArray()){
            if(curr.children[c-'a']==null)return 0;
            curr=curr.children[c-'a'];
        }
        return dfs(curr);
        
    }
    private int dfs(TrieNode curr){
        int sum=0;
        for(int i =0 ;i<26;i++){
            if(curr.children[i] != null){
                sum += dfs(curr.children[i]);
            }
        }
        return sum+curr.weight;
    }
}
//My Solution: Record oldVal by map and use trie to store the prefix sum
class MapSum{
	class TrieNode{
		int val = 0;
		TrieNode[] kids = new TrieNode[26];
		}
TrieNode root;
	HashMap<String, Integer> cache;
	public MapSum(){
	root = new TrieNode();
cache = new HashMap();

}

public void insert(String key, int val){
	int oldVal = cache.getOrDefault(key, 0);
	cache.put(key, val);
	val -= oldVal;
	TrieNode curNode = root;
	for(char c : key.toCharArray()){
		int idx = c - 'a';
	if(curNode.kids[idx] == null){
	curNode.kids[idx] = new TrieNode();
}
curNode.val += val;
curNode = curNode.kids[idx];
}
curNode.val += val;
}

public int sum(String prefix){
	TrieNode curNode = root;
	for(char c : prefix.toCharArray()){
	int idx = c - 'a';
	if(curNode.kids[idx] == null){
	return 0;
}
curNode = curNode.kids[idx];
}

return curNode.val;
}
}
