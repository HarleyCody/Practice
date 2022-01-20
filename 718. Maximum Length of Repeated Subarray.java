//Best Solution: rolling Hash the array with long in different length
//Binary search to find max length by comparing hash with length.
class Solution {
   public int findLength(int[] nums1, int[] nums2) {
        long p = 131l; // 0 <= nums1[i], nums2[i] <= 100
        int n = nums1.length;
        int m = nums2.length;
        int minl = Math.min(n,m);
        // 一、nums1和nums2的 字符串哈希预处理: 求hash1[],hash2[],pArr[]
        long[] hash1 = new long[n+1];
        long[] hash2 = new long[m+1];
        long[] pArr = new long[minl+1];
        for(int i=1; i<n+1; i++) {
            hash1[i] = hash1[i-1]*p + (nums1[i-1]+1); // +1(可能nums1[i], nums2[i]=0), nums1[i-1](nums1没法在前面加“ ”)
        }
        for(int i=1; i<m+1; i++) {
            hash2[i] = hash2[i-1]*p + (nums2[i-1]+1);
        }
        pArr[0] = 1;
        for(int i=1; i<minl+1; i++) {
            pArr[i] = pArr[i-1]*p;
        }

        // 二、二分法求最大长度min, 最后l==r时,l/r就是最大长度
        int l=0;
        int r=minl;
        int mid = 0;
        while(l<=r) {
            mid = (l+r+1)/2;
            if(hasSame(mid, hash1, hash2, pArr)) { // mid长度可以,尝试更大长度[mid,r]
                l = mid+1;
            } else { // mid长度不可以,尝试更大小长度[l,mid-1]
                r = mid -1;
            }
        }
        return l-1;
    }

    // num1中某长度为len的串, 有无与num2中子串相同的
    public boolean hasSame(int len, long[] hash1, long[] hash2, long[] pArr) {
        HashSet<Long> set = new HashSet<>();
        for(int i=1; i<=hash1.length-len; i++) {
            set.add(getLR(hash1, pArr, i, i+len-1));
        }
        for(int i=1; i<=hash2.length-len; i++) {
            if(set.contains(getLR(hash2, pArr, i, i+len-1))) return true;
        }
        return false;
    }

    public long getLR(long[] hash, long[] pArr, int l, int r) {
        return hash[r]-hash[l-1]*pArr[r-l+1];
    }
}

//My Solution: Dynamic programming, current max length depends on the previous max length;
class Solution{
	public int findLength(int[] nums1, int[] nums2){
		int len1 = nums1.length;
		int len2 = nums2.length;
		
        int[][] dp = new int[len1][len2];
        int ans = 0;
        for(int i = 0; i < len1; ++i){
            for(int j = 0; j < len2; ++j){
                if(nums1[i]  == nums2[j]){
                    int prev = 0;
                    if(i > 0 && j > 0){
                        prev = dp[i - 1][j - 1];
                    }
                    dp[i][j] = Math.max(1 + prev, dp[i][j]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
