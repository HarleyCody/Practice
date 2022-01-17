//My Solution: Parse recursively. if char is letter append it.
//              if char is { concatenate cur with next
//              if char is , finish the build of current item, add item or cur set into ans
//              if char is } finsih the build of current item, add item or cur set into ans and break recursion
class Solution {
    char[] chs;
    int idx = 0;
    char[] brace = {'{', '}'};
    public List<String> braceExpansionII(String expression) {
        chs = expression.toCharArray();
        List<String> ans = new ArrayList(parse());
        Collections.sort(ans);
        return ans;
    }
    
    private Set<String> parse(){
        Set<String> ans = new HashSet();
        Set<String> cur = new HashSet();
        StringBuilder item = new StringBuilder();
        while(idx < chs.length){
            if('a' <= chs[idx] && chs[idx] <= 'z'){
                item.append(chs[idx]);
            }else if(chs[idx] == brace[0]){
                ++idx;
                if(item.length() > 0){
                    cur = concatenate(cur, item);
                    item.setLength(0);
                }
                Set<String> next = parse();
                cur = concatenate(cur, next);
            }else if(chs[idx] == brace[1]){
                if(item.length() > 0){
                    cur = concatenate(cur, item);
                    item.setLength(0);
                }
                if(!cur.isEmpty()){
                    ans.addAll(cur);
                    cur.clear();
                }
                
                break;
            }else{
                if(item.length() > 0){
                    cur = concatenate(cur, item);
                    item.setLength(0);
                }
                if(!cur.isEmpty()){
                    ans.addAll(cur);
                    cur.clear();
                }
            }
            ++idx;
        }
        if(idx == chs.length){
            if(item.length() > 0){
                cur = concatenate(cur, item);
            }
            if(!cur.isEmpty()){
                ans.addAll(cur);
            }
        }
        
        return ans;
    }
    
    private Set<String> concatenate(Set<String> left, Set<String> right){
        if(left.size() == 0) return new HashSet(right);
        Set<String> ans = new HashSet();
        StringBuilder sb = new StringBuilder();
        for(String prefix : left){
            sb = new StringBuilder(prefix);
            for(String latter : right){
                sb.append(latter);
                ans.add(sb.toString());
                sb.setLength(prefix.length());
            }
        }
        return ans;
    }

    private Set<String> concatenate(Set<String> left, StringBuilder right){
        Set<String> ans = new HashSet();
        if(left.isEmpty()) {
            ans.add(right.toString());
            return ans;
        }
        StringBuilder sb = new StringBuilder();
        for(String prefix : left){
            sb = new StringBuilder(prefix);
            sb.append(right);
            ans.add(sb.toString());
        }
        
        return ans;
    }
}
