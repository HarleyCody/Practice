_____________________________________________________Best Solution___________________________________________________________
class Solution {
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList();
        if(A == null || A.length == 0) return res;
        
        // record frequency of char
        int[] count = new int[26];
        Arrays.fill(count, Integer.MAX_VALUE);
        
        // update recorder(count)
        for(int i=0;i<A.length;i++) {
            helper(A[i].toCharArray(), count);
        }
        // count record common frequency of char in all strings.
        for(int i=0;i<26;i++){
            // times of char in i + 'a';
            for(int j=0;j<count[i];j++){
                res.add(String.valueOf((char)(i+'a')));
            }
        }
        return res;        
    }
    
    private void helper(char[] a, int[] bb) {
        int[] aa = new int[26];
        for(char c : a) {
            aa[c - 'a']++;
        }
        // get minimal frequency of char, 0 still will be 0.
        for(int i=0;i<26;i++){
            bb[i] = Math.min(aa[i], bb[i]);
        }
    }  
}
_____________________________________________________My Solution_____________________________________________________________
class Solution {
    public List<String> commonChars(String[] A) {
        List<String> ans = new LinkedList();
        if(A.length == 0) return ans;
        
        List<Character> pre = new LinkedList();
        for(Character c : A[0].toCharArray()){
            pre.add(c);
        }
        
        for(String s : A){
            
            List<Character> common = new LinkedList();
            
            for(char c : s.toCharArray()){
                if(pre.contains(c)){
                    common.add(c);
                    pre.remove((Character)c);
                }
            }
            pre.clear();
            pre.addAll(common);
        }
        for(char c : pre){
            ans.add(Character.toString(c));
        }
        return ans;
    }
}
