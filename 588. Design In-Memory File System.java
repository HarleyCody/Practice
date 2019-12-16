______________________________________________________My Solution(Trie)________________________________________________________
class FileSystem {
// potential improvement, use char[27]  instead of hashmap, but cannot save name; should use dfs to get all name;
    class Trie{
        HashMap<String, Trie> child = new HashMap();
        StringBuilder content = new StringBuilder();
        String fileName;
    }
    Trie root;
    public FileSystem() {
        this.root = new Trie();
    }
    
    public List<String> ls(String path) {
        Trie cur = reachToDst(path);
        List<String> ans = new ArrayList();
        if(cur.fileName != null) return new ArrayList(){{add(cur.fileName);}};
        for(String dir : cur.child.keySet()){
            ans.add(dir);
        }
        Collections.sort(ans);
        return ans;
    }
    
    public void mkdir(String path) {
        reachToDst(path);
    }
    
    public void addContentToFile(String filePath, String content) {
        Trie cur = reachToDst(filePath);
        int l = filePath.lastIndexOf("/");
        cur.content.append(content);
        cur.fileName = filePath.substring(l + 1);
    }
    
    public String readContentFromFile(String filePath) {
        Trie cur = reachToDst(filePath);
        return cur.content.toString();
    }
    // get node
    private Trie reachToDst(String path){
        Trie cur = root;
        String[] dirs = path.split("/");
        for(int i = 1; i < dirs.length; ++i){
            Trie next;
            if(!cur.child.containsKey(dirs[i])){
                next = new Trie();
                cur.child.put(dirs[i], next);
            }else{
                next = cur.child.get(dirs[i]);
            }
            cur = next;
        }
        return cur;
    }
}
