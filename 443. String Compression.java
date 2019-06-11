_________________________________________________________Better Solution___________________________________________________________________
class Solution {
public int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            
            //find the end of repeat char
            while(index < chars.length && chars[index] == currentChar){
                ++index;
                ++count;
            }
            // record head
            chars[indexAns++] = currentChar;
            // record count
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray()) 
                    chars[indexAns++] = c;
        }
        return indexAns;
    }
}

____________________________________________________________My Solution____________________________________________________________________
class Solution {
    public int compress(char[] chars) {
        if(chars.length == 0) return 0;
        char[] appendChars = new char[chars.length + 1];
        for(int i = 0; i < chars.length; ++i){
            appendChars[i] = chars[i]; 
        }
        List<Character> ans = new ArrayList();
        char start = chars[0];
        int count = 0;
        ans.add(start);
        for(char c : appendChars){
            if(c == start){
                ++count;
            }else{
                if(count > 1){
                    char [] digits = String.valueOf(count).toCharArray();
                    for(char d : digits) ans.add(d);
                    count = 1;
                }
                if(c == appendChars[appendChars.length-1])break;
                start = c;
                ans.add(start);
            }
        }
        for(int i = 0; i < ans.size(); ++i){
            chars[i] = ans.get(i);
        }
        return ans.size();
    }
}
