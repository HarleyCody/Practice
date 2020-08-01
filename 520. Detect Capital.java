_____________________________________________________________________________________My Solution______________________________________________________________________________
class Solution {
// AllLowerCase, FirstUpperCase or AllUpperCase
    public boolean detectCapitalUse(String word) {
        char[] chs = word.toCharArray();
        boolean hasLowerCase = false, hasUpperCase = false, allUpperCase = false;
        for(char c : chs){
            if(Character.isUpperCase(c)){
                
                if(hasUpperCase){
                    allUpperCase = true;
                }
                if(hasLowerCase){
                    return false;
                }
                hasUpperCase = true;
            }else{
                if(allUpperCase){
                    return false;
                }
                hasLowerCase = true;
            }
        }
        return true;
    }
}
