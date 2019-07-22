________________________________________________________Best Solution__________________________________________________________
class Solution {
    // change string to char array, find a word and swap char in a word 
    public String reverseWords(String s) {
        char[] c = s.toCharArray();
        int start = 0, end = 0;
        while((end = s.indexOf(' ', start)) != -1){
            reverse(c, start, end - 1);
            // move to begin of next word, if only move to end, next end will be == start;
            start = end + 1;
        }
        reverse(c, start, s.length() - 1);
        return new String(c);
    }
    private void reverse(char[] c, int start, int end){
        while(start < end){
            char tem = c[start];
            c[start++] = c[end];
            c[end--] = tem;
        }
    }
}
__________________________________________________________My Solution__________________________________________________________
class Solution {
    // Split String to string array( string builder cannot use split method)
    public String reverseWords(String s) {
        String[] recorder = s.split(" ");
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < recorder.length; ++i){
            // convert string to string builder in order to use reverse method in StringBuilder
            StringBuilder w = new StringBuilder(recorder[i]);
            ans.append(w.reverse());
            // if its last word, do not append space.
            if(i != recorder.length - 1)
                ans.append(' ');
        }
        // output string;
        return ans.toString();
    }
}
