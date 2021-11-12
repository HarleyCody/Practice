//Best Solution: String Builder delete to find 
class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int index = sb.indexOf(part);
        while(index>-1){
            sb.delete(index,index+part.length());
            index = sb.indexOf(part, Math.max(0, index - part.length() + 1));
        }
        return sb.toString();
    }
}
//My Solution: cut and find again;
class Solution {
    public String removeOccurrences(String s, String part) {
        int idx = s.indexOf(part);
        int len = part.length();
        while(idx >= 0){
            s = s.substring(0, idx) + s.substring(idx + len);
            idx = s.indexOf(part);
        }
        return s;
    }
}
