class Solution {
    //freq[i] num of char in i
    //chs[i] chars in [i], correspond with freq
    // only remove k duplciate char => num % k for freq
    public String removeDuplicates(String S, int k) {
        int len = S.length();
        int[] freq = new int[len];
        char[] chs = S.toCharArray();
        
        int s = 0, e = 0, ptr = 0;
        while(e < len){
            int num = 0;
            while(e < len && chs[e] == chs[s]){
                ++e;
                ++num;
            }
            freq[ptr] = num % k;
            chs[ptr] = chs[s];
            if(ptr > 0 && chs[ptr] == chs[ptr - 1]){
                freq[ptr - 1] += freq[ptr];
                freq[--ptr] %= k;
            }
            // check cur is valid
            if(freq[ptr] == 0){
                --ptr;
            }
            ++ptr;
            s = e;
        }
        
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < ptr; ++i){
            int num = freq[i];
            char c = chs[i];
            while(num-- > 0){
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
