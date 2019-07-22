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
