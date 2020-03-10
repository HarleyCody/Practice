__________________________________________________________Best Solution________________________________________________________
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        };
        // sort with length ascending
        Arrays.sort(folder,comp);
        List<String> res = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        boolean add = true;
        for(String f : folder) {
            add = true;
            for(int i=2;i < f.length(); ++i) {
                // check every section, make sure it is unique path
                if(f.charAt(i) == '/' && set.contains(f.substring(0,i))) {
                    add = false;
                    break;
                }
            }
            if(add){
                //add head and set in header
                res.add(f);
                set.add(f);
            }
        }
        return res;
    }
}
____________________________________________________My Solution_______________________________________________________________
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // assure root will be the first of sub folders
        // only add new root(header) to ans;
        Arrays.sort(folder);
        int len = folder.length;
        String header = " ";
        HashSet<String> ans = new HashSet();
        for(int i = 0; i < len; ++i){
            if(folder[i].equals(header)){
                continue;
            }else if(!folder[i].startsWith(header) || 
                     (folder[i].startsWith(header) && folder[i].charAt(header.length()) != '/')){
                header = folder[i];
                ans.add(header);
            }
        }
        return new ArrayList(ans);
    }
}
