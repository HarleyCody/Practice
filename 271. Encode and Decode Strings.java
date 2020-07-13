____________________________________________________________________________Best Solution______________________________________________________________________________
public class Codec {
    // same as solution1 but decode string to list by substring() not read and append one by one
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder ans = new StringBuilder();
 
        for(String str : strs){
            ans.append(str.length());
            ans.append(':');
            ans.append(str);
        }
        return ans.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ans = new ArrayList();
        
        int idx = 0, len = s.length();
        char[] chs = s.toCharArray();
        while(idx < len){
            int strLen = 0;
            while(idx < len && chs[idx] != ':'){
                strLen = strLen * 10 + chs[idx++] - '0';
            }
            ++idx;
            
            int endIdx = idx + strLen;
            ans.add(s.substring(idx, endIdx));
            idx = endIdx;
        }
        return ans;
    }
}
______________________________________________________________________________My Solution______________________________________________________________________________
public class Codec {
    // format strlength: str
    // read length first and then read length number of chars to build a string
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder ans = new StringBuilder();
 
        for(String str : strs){
            ans.append(str.length());
            ans.append(':');
            ans.append(str);
        }
        return ans.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ans = new ArrayList();
        
        int idx = 0, len = s.length();
        char[] chs = s.toCharArray();
        while(idx < len){
            int strLen = 0;
            while(idx < len && chs[idx] != ':'){
                strLen = strLen * 10 + chs[idx++] - '0';
            }
            ++idx;
            
            int endIdx = idx + strLen;
            StringBuilder str = new StringBuilder();
            while(idx < endIdx){
                str.append(chs[idx++]);
            }
            ans.add(str.toString());
        }
        return ans;
    }
}
