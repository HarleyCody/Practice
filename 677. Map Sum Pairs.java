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
