class Solution {
    // check vowels brute force will be faster than put vowels in hashSet for checking
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();  
        int l = 0, r = chs.length - 1;
        while(l < r){
            while(l < r && chs[l] != 'a' && chs[l] != 'e' && chs[l] != 'i' && chs[l] != 'o' && chs[l] != 'u' &&
                 chs[l] != 'A' && chs[l] != 'E' && chs[l] != 'I' && chs[l] != 'O' && chs[l] != 'U'){
                ++l;
            }
            
            while(l < r && chs[r] != 'a' && chs[r] != 'e' && chs[r] != 'i' && chs[r] != 'o' && chs[r] != 'u' &&
                 chs[r] != 'A' && chs[r] != 'E' && chs[r] != 'I' && chs[r] != 'O' && chs[r] != 'U'){
                --r;
            }
            if(r == l){
                break;
            }
            char tmp = chs[l];
            chs[l] = chs[r];
            chs[r] = tmp;
            ++l;
            --r;
        }
        return new String(chs);
    }
}
