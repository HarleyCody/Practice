______________________________________________________Best Solution_____________________________________________________________________
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int p = 0;
        int i = 0;
        int j = 0;
        while (i < nums1.length || j < nums2.length) {
            int n1 = i < nums1.length ? nums1[i] : Integer.MAX_VALUE;
            int n2 = j < nums2.length ? nums2[j] : Integer.MAX_VALUE;
            
            if (n1 < n2) {
                nums[p] = n1;
                i++;
            } else {
                nums[p] = n2;
                j++;
            }
            p++;
        }
        if (nums.length % 2 == 0) {
           return (nums[nums.length/2 - 1] + nums[nums.length/2])/2.0;
        } else {
            return nums[nums.length/2];
        }
    }
}
_________________________________________________________My Solution____________________________________________________________________
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;// median of A
            int j = halfLen - i; // median of B
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
