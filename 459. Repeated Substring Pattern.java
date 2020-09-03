___________________________________________________________________________Best Solution______________________________________________________________________________
class Solution {
// prime within 100 can construct any numbers by multiply it
    public boolean repeatedSubstringPattern(String s) {
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int l = s.length();
        for (int i : primes) {
            if (i > l) break;
            if (l % i == 0) {
                boolean valid = true;
                int j = l / i;
                for (int n = l; n > j; n -= j) {
                    if (!s.substring(n - j, n).equals(s.substring(n - 2 * j, n - j))) {
                        valid = false;
                        break;
                    }
                }
                if (valid) return true;
            }
        }
        return false;
    }
}
___________________________________________________________________________KMP Solution______________________________________________________________________________
class Solution {
    // kmp calcualte repeated substring
    // len smallest length of root string;
    // n = target string
    public boolean repeatedSubstringPattern(String str) {
        //This is the kmp issue
        int[] prefix = kmp(str);
        print(prefix);
        int len = prefix[str.length()-1];
        int n = str.length();
        return (len > 0 && n%(n-len) == 0);
    }
    private int[] kmp(String s){
        int len = s.length();
        int[] res = new int[len];
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        res[0] = 0;
        while(i < len && j < len){
            if(ch[j] == ch[i]){
                res[j] = i + 1;
                i++;
                j++;
            }else{
                if(i == 0){
                    res[j] = 0;
                    j++;
                }else{
                    // back to previous valid common prefix and continue extend
                    i = res[i - 1];
                }
            }
        }
        return res;
    }
}
______________________________________________________________________________My Solution______________________________________________________________________________
class Solution {
    // check when it is a possible start
    public boolean repeatedSubstringPattern(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        
        for(int i = 1; i <= len / 2; ++i){
            if(chs[i] == chs[0] && check(chs, i, len)){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean check(char[] chs, int s, int len){
        int l = 0, r = s;
        while(s < len){
            l = 0;
            while(l < r && s < len && chs[l] == chs[s]){
                ++l;
                ++s;
            }
            
            if(l != r){
                return false;
            }
        }
        
        return l == r && s == len;
    }
}
