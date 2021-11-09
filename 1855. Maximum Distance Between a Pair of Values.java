//My Solution: two pointers and sliding window 
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        while(0 <= j && 0 <= i){
            i = Math.min(i, j);
            while(0 < i && nums2[j] >= nums1[i - 1]){
                --i;
            }
            if(nums1[i] <= nums2[j]){
                ans = Math.max(j - i, ans);
            }
            --j;
        }
        
        return ans;
    }
}
//Same Solution from left to right: two pointers move pointers based on condition. Move n1 until meet condition and expand
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int i = 0,j=0;
        int ans = 0;
        while(i<n2){
            
            if(i<j){
                i = j;
            }
            if(j>=n1 || i>=n2){
                break;
            }
            if(nums2[i]<nums1[j]){
                j++;
            }else{
                ans = Math.max(i-j, ans);
                i++;
            }
        }
        return ans;
    }
}
