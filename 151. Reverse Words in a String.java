_______________________________________________________________Better Solution______________________________________________________________
class Solution {
    public String reverseWords(String s) {
        
        StringBuilder ans = new StringBuilder();
        String[] string = s.split(" ");
        // append string from backworad, so the string is reversed
        for(int i = string.length - 1; i >= 0; --i){
            // avoid adding " ".
            if(string[i].trim().isEmpty())continue;
            // add space between word, should not add " " before first word;
            if(i < string.length - 1)
                ans.append(" ");
            // StringBuilder can append string directly
            ans.append(string[i]);
        }
        return ans.toString();
    }
}
____________________________________________________________________My Solution____________________________________________________________
class Solution {
    public String reverseWords(String s) {
        if(s.trim().isEmpty()) return s.trim();
        s = s.trim();//could be deleted
        StringBuilder ans = new StringBuilder();
        String[] string = s.split(" ");
        for(int i = 0; i < string.length; ++i){// should travel from back
            if(string[i].trim().isEmpty())continue;
            StringBuilder sb = new StringBuilder(string[i].trim());// string builder can append string, so could be deleted
            if(i > 0)
                sb.append(" ");
            ans = sb.append(ans);// costs time should avoid use "=" append directly.
        }
        return ans.toString();
    }
}
