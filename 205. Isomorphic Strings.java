_______________________________________________________________My Solution_______________________________________________________________
class Solution {
    //record mapping of two string
    //index start from 1 as 0 means null;
    // 129 for all chars
    public boolean isIsomorphic(String s, String t) {
        int[] sMap = new int[129];
        int[] tMap = new int[129];
        int sLen = s.length(), tLen = t.length();
        
        for(int i = 0; i < sLen; ++i){
            int key = s.charAt(i) + 1;
            int val = t.charAt(i) + 1;
            if(sMap[key] == 0 && tMap[val] == 0){
                sMap[key] = val;
                tMap[val] = key;
            }else if(sMap[key] != val || tMap[val] != key){
                return false;
            }
        }
        return true;
    }
}
