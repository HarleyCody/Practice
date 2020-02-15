_____________________________________________________________Best Solution_______________________________________________________________
// edit flag assure only edit once.
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        int diff = Math.abs(len1 - len2);
        if(diff > 1) return false;
        boolean edited = false;
        int i = 0, j = 0;
        while(i < len1 && j < len2){
            // need edit
            if(s.charAt(i) != t.charAt(j)){
                
                if(edited) return false;// second time edit
                // first time edit;
                if(diff == 0){
                    ++i;
                    ++j;
                }else if(len1 > len2){
                    ++i;
                }else{
                    ++j;
                }
                edited = true;
            }else{
                ++i;
                ++j;
            }
        }
        // only one left;
        if(len1 - i + len2 - j == 1)edited = true;
        // have to edit once
        return edited;
    }
}
