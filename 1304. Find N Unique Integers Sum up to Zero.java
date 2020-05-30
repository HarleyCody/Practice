class Solution {
    // l are negative r are postive, middle is 0 if len is odd
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int idx = 0;
        
        int l = 0, r = n - 1;
        while(l < r){
            ans[l++] = -n;
            ans[r--] = n;
            --n;
        }
        if(l == r){
            ans[r] = 0;
        }
        return ans;
    }
}
