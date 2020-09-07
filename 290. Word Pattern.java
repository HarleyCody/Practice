____________________________________________________________________________________My Solution____________________________________________________________________________________
// bidirection map, to match the chars
class Solution {
    public boolean wordPattern(String pattern, String str) {
        char[] chs = pattern.toCharArray();
        int len = chs.length;
        String[] words = str.split(" ");
        
        if(len != words.length){
            return false;
        }
        
        String[] map = new String[256];
        HashMap<String, Character> revMap = new HashMap();

        
        
        for(int i = 0; i < len; ++i){
            String tar = map[chs[i]];
            if(tar == null){
                map[chs[i]] = words[i];
            }else if(!words[i].equals(tar)){
                return false;
            }
            
            char tChar = revMap.getOrDefault(words[i], (char)0);
            if(tChar == 0){
                revMap.put(words[i], chs[i]);
            }else if(tChar != chs[i]){
                return false;
            }
        }
        
        return true;
    }
}
