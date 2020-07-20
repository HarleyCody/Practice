___________________________________________________________________________My Solution_____________________________________________________________________________________
class Solution {
    // Compare one by one and update ansString
    public String findLongestWord(String s, List<String> d) {
        if(s.length() == 0){
            return ""; 
        }
        String ans = "";
        for(String str : d){
            if(check(str, s)){
                int strLen = str.length();
                int ansLen = ans.length();
                if(strLen > ansLen || (strLen == ansLen && ans.compareTo(str) > 0)){
                    ans = str;
                }
            }
        }
        return ans;
    }
    
    private boolean check(String src, String tar){
        int lastPos = -1;
        int len = src.length();
        for(int i = 0; i < len; ++i){
            int curIdx = tar.indexOf(src.charAt(i), lastPos + 1);
            if(curIdx == -1){
                return false;
            }
            lastPos = curIdx;
        }
        
        return true;
    }
}
