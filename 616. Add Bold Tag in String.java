________________________________________________________________Best Solution______________________________________________________________
class Solution {
    public String addBoldTag(String s, String[] dict) {
     
        if(dict.length == 0) return s;
        
        boolean[] mask = new boolean[s.length()];
        
        // lable all dict words in string as true;
        int a = 0;
        for (String str : dict) {
            a = s.indexOf(str, 0);
            while (a >= 0) {
                int st = a;
                int ed = st + str.length();
                while(st < ed) mask[st++] = true;
                a = s.indexOf(str, a + 1);
            }
        }
        
        StringBuffer sb = new StringBuffer();
        
        int i = 0;
        
        // append no bold words first, then append bold words and update start and keep searching
        for (int k = 0 ; k < mask.length; k++) {
            if(mask[k] == false ) continue;
            sb.append(s , i, k);  // Fill the string not part of dic
            sb.append("<b>");
            int end = k;
            while (end < mask.length && mask[end] == true) end++; // find the next false index
            sb.append(s, k, end);
            sb.append("</b>");   
            i = end;
            k = end;
        }
        // append last part
        if(i != s.length()) sb.append(s, i, s.length());
        return sb.toString();
    }
}
_________________________________________________________________General Solution______________________________________________________________
public class Solution {
    // boolean array records the char in string is bold or not
    // if end > i means current char is bold
    
    // step 1 get bold array
    // step 2 compose bold String by bold array
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }
        
        return result.toString();
    }
}
___________________________________________________________________My Solution______________________________________________________________
class Solution {
    // find the end point for every string bold the string
    // max end point of string can be extended by start with any substring of string but with larger end idx
    Set<String> wordSet;
    Set<Integer> wordLen;
    int strLen;
    public String addBoldTag(String str, String[] dict) {
        
        StringBuilder ans = new StringBuilder();
        wordSet = new HashSet(Arrays.asList(dict));
        wordLen = new HashSet();
        for(String word : dict){
            wordLen.add(word.length());
        }
        
        strLen = str.length();
        
        int start = 0, end = 0;
        while(start < strLen){
            int s = start, maxE = start;
            for(int len : wordLen){
                int e = Math.min(s + len, strLen);
                String w = str.substring(s, e);
                if(wordSet.contains(w)){
                    maxE = Math.max(e, maxE);
                }
            }
            //System.out.println("Matched base is " + str.substring(s, maxE));
            if(maxE == s){
                ++start;
                ans.append(str.charAt(start - 1));
            }else{
                maxE = appendWord(str, s, maxE);
                ans.append("<b>");
                ans.append(str.substring(start, maxE));
                ans.append("</b>");
                start = maxE;
            }
        }
        
        return ans.toString();
    }
    private int appendWord(String str, int start, int end){
        int maxE = end;
        for(int s = start + 1; s <= maxE; ++s){
            if(s == strLen){
                break;
            }
            for(int len : wordLen){
                //System.out.println("Start at " + str.charAt(s) + " with len" + len);
                int e = s + len;
                if(e > strLen){
                    continue;
                }
                String w = str.substring(s, e);
                //System.out.println("word is " + w);
                if(wordSet.contains(w)){
                    maxE = Math.max(e, maxE);
                }
            }
        }
        return maxE;
    }
}
