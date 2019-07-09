class Solution {
    public String simplifyPath(String path) {
        // record valid dirs
        List<String> recorder = new ArrayList();
        String[] dirs = path.split("/");
        
        // filter and record valid dir
        for(int i = 0; i < dirs.length; ++i){
            if(dirs[i].equals("..")){
                if(!recorder.isEmpty())
                    recorder.remove(recorder.size() - 1);
            }else if(dirs[i].equals(".") || dirs[i].equals("")){
                continue;
            }else recorder.add(dirs[i]);
        }
        // recover to ans string
        if(recorder.isEmpty()) return "/";
        
        StringBuilder ans = new StringBuilder();
        
        while(!recorder.isEmpty()){
            ans.append("/");
            ans.append(recorder.remove(0));
        }
        return ans.toString();
    }
}
