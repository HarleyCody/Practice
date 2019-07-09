class Solution {
    public String reorganizeString(String S) {
        //record frequency of char
        int[] recorder = new int[26];
        for(char c : S.toCharArray()){
            ++recorder[c - 'a'];
        }
        
        int idx = mostFrequentChar(recorder);
        if(recorder[idx] > (S.length() - 1)/2 + 1) return "";
        // construct ans String
        StringBuilder ans = new StringBuilder();
        // construct basic string, with most frequent char
        while(recorder[idx] > 0){
            ans.append((char)(idx + 97));
            --recorder[idx];
        }
        // insert in second place
        int pos = 1;
        while(ans.length() != S.length()){
            // next most frequent char
            idx = mostFrequentChar(recorder);
            char c = (char) (idx + 97);
            // insert all of them into basic string
            while(recorder[idx] > 0){
                if(pos >= ans.length()) pos = 0; // reset to 0(first char, if necessary) 1 falied for aabb, which means two most frequent chars have same frequency. 
                ans = ans.insert(pos,c);
                --recorder[idx];
                // move pos to next insert place
                pos +=2;
            }
        }
        return ans.toString();
    }
    // get index of mostFrequentChar
    private int mostFrequentChar(int[] chars){
        int max = 0, ans = -1;
        for(int i = 0; i < chars.length; ++i){
            if(max < chars[i]){
                max = chars[i];
                ans = i;
            }
        }
        return ans;
    }
}
