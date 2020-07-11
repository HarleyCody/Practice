__________________________________________________________________Concise Solution____________________________________________________________________
class Solution {
    // only one number from {A[0], B[0]} can form equal value arr
    // if A can form assured that B cannot form the value, so just return ans when All is A
    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == A[0] || B[i] == A[0]); ++i) {
            if (A[i] != A[0]) a++;
            if (B[i] != A[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == B[0] || B[i] == B[0]); ++i) {
            if (A[i] != B[0]) a++;
            if (B[i] != B[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }
        return -1;
    }
}
____________________________________________________________________My Solution____________________________________________________________________
class Solution {
    // A either be A[0] or B[0];
    // B either be A[0] or B[0];
    // try four times to get min
    int[] freq = new int[6];
    int len = 0;
    public int minDominoRotations(int[] A, int[] B) {
        int tar0 = A[0], tar1 = B[0];
        len = A.length;
        
        int ans = len + 1;
        ans = Math.min(helper(tar0, A, B), ans);
        ans = Math.min(helper(tar1, A, B), ans);
        ans = Math.min(helper(tar0, B, A), ans);
        ans = Math.min(helper(tar1, B, A), ans);
        
        return ans == len + 1 ? -1 : ans;
    }
    
    private int helper(int tar, int[] main, int[] sup){
        int ans = 0;
        for(int i = 0; i < len; ++i){
            if(main[i] != tar && sup[i] != tar){
                return len + 1;
            }
            if(main[i] != tar){
                ++ans;
            }
        }
        return ans;
    }
}
