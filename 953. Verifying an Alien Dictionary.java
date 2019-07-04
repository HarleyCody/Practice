class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // int[] order = new int[26];
        // for(int i = 0; i < ){
        //     int charInt = order.charAt()
        // }
        String pre = words[0];
        for(int i = 1; i < words.length; ++i){
            String sec = words[i];
            int len = Math.min(pre.length(), sec.length());
            for(int s = 0; s < len; ++s){
                int idxPre = order.indexOf(pre.charAt(s));
                int idxSec = order.indexOf(sec.charAt(s));
                // valid, check next pair words( sec, third); after this loop, pre will be sec, sec will be third.
                if(idxPre < idxSec) break;
                // common part
                else if (idxPre == idxSec){
                    //common part and pre is longer :flase
                    if(pre.length() > sec.length()) return false;
                    // valid, continue to check next char;
                    else continue;
                }
                //(idxPre > idxSec), pre should be behind idxSec.
                else return false;
            }
            //pre will be sec, sec will be third.
            pre = sec;
        }
        return true;
    }
}
