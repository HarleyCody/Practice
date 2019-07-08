______________________________________________________Best Solution____________________________________________________________
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] recorder = new int[26];
        // increase based on candidate
        for(char c : s.toCharArray()){
            ++recorder[c - 'a'];
        }
        // decrease based on target
        for(char c : t.toCharArray()){
            --recorder[c - 'a'];
        }
        // recorder should be all zero
        for(int i : recorder){
            if(i != 0) return false;
        }
        return true;
    }
}
_______________________________________________________My Solution_____________________________________________________________
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] recorder = new int[26];
        for(int i = 0; i < s.length(); ++i){
            ++recorder[s.charAt(i) - 'a'];
            --recorder[t.charAt(i) - 'a'];
        }
        for(int i = 0; i < 26; ++i){
            if(recorder[i] != 0) return false;
        }
        return true;
    }
}
