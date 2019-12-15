_______________________________________________________Best Solutions__________________________________________________________
class Solution {
// find first i where chs[i] < chs[i + 1];
// find minimal val j after i where chs[i + 1] > chs[j] > chs[i]
// swap i j, and sort chars after i with ascending order.
// use long to avoid overflow of int.

    public int nextGreaterElement(int n) {
        char[] chs = String.valueOf(n).toCharArray();
        
        int l = chs.length - 1;
        while(0 < l && chs[l] <= chs[l - 1]){
            --l;
        }
        --l;
        if(l < 0) return -1;
        int i = chs.length - 1, r = l + 1;
        while(l < i){
            if(chs[i] < chs[r] && chs[i] > chs[l]){
                r = i;
            }
            --i;
        }
        char temp = chs[l];
        chs[l] = chs[r];
        chs[r] = temp;
        Arrays.sort(chs, l + 1, chs.length);
        long ans = Long.parseLong(new String(chs));
        return ans > Integer.MAX_VALUE ? -1 : (int)ans;
    }
}
_______________________________________________________DFS Solutions(BruteForce)_______________________________________________
class Solution {
// recorder num of digits, compse them to number and comapre with N and choose minimal value(compare with previous);
    int ans = -1;
    int med = 0;
    public int nextGreaterElement(int n) {
        char[] chs = String.valueOf(n).toCharArray();
        int[] digits = new int[10];
        for(char c : chs){
            ++digits[c - '0'];
        }
        for(int i = 1; i < 10; ++i){
            med = 0;
            if(digits[i] != 0 && i >= chs[0] - '0'){
                med += i;
                --digits[i];
                dfs(digits, chs.length, n, 1);
                ++digits[i];
                if(ans != -1) break;
            }
        }
        return ans;
    }
    private void dfs(int[] digits, int lenOfN, int n, int lenOfAns){
        if(lenOfAns == lenOfN && med > n){
            ans = ans == -1 ? med : Math.min(ans, med); 
        }
        for(int i = 0; i < digits.length; ++i){
            int temp = med;
            if(digits[i] != 0){
                --digits[i];
                med = med * 10 + i;
                dfs(digits, lenOfN, n, lenOfAns + 1);
                ++digits[i];
            }
            med = temp;
        }
    }
}
