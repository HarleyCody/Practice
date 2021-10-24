//My Solution: like trie, hashMap to record [kid string, kid node] pairs
class FileSystem {
    class Node{
        int val;
        HashMap<String, Node> kids = new HashMap();
    }
    Node root;
    Node find;
    Set<String> paths;
    public FileSystem() {
        root = new Node();
        paths = new HashSet();
    }
    
    public boolean createPath(String path, int value) {
        String[] dirs = path.split("/");
        int len = dirs.length;
        int idx = 1;
        find = root;
        while(idx < len){
            if(!find.kids.containsKey(dirs[idx])){
                if(idx < len - 1) return false;
                find.kids.put(dirs[idx], new Node());                
            }
            find = find.kids.get(dirs[idx++]);
        }
        if(paths.add(path)){
            find.val = value;
            return true;
        }
        return false;
    }
    
    public int get(String path) {
        if(!paths.contains(path)) return -1;
        
        find = root;
        String[] dirs = path.split("/");
        int len = dirs.length;
        int idx = 1;
        while(idx < len){
            find = find.kids.get(dirs[idx++]);
        }
        return find.val;
    }
}
