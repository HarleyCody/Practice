___________________________________________________________________Best Solution_________________________________________________________
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                // largest element among dp, i is len. in current dp
                ++len;
            }
        }
        return len;
    }
}
_____________________________________________________Better Solution with Binary Search___________________________________________________
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int cur = -1;
        for (int i = 0; i < nums.length; i++) {
            int index = findIndex(nums[i], dp, cur);
            dp[index] = nums[i];
            System.out.println();
            for(int j = 0; j < dp.length; ++j){
                System.out.print(dp[j]+",");
            }
            if (index > cur)
                cur++;
        }
        return cur + 1;
    }
    
    int findIndex(int val, int[] dp, int cur) {
        int low = 0, high = cur;
        while (low <= high) {
            int mid = (high - low) / 2 + low; // without + low will enter endless circle
            if (dp[mid] < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            System.out.print("mid" + mid+",");
        }
        return low;
    }
}
__________________________________________________________My Solution______________________________________________________________________
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length, ans = 0, max = 1;
        if(len < 2) return len;
        int dp[] = new int[len];
        dp[0] = 1;
        if( len < 2) return len;
        for(int i = 1; i < len; ++i){
            max = 0;
            for(int j = 0; j < i; ++j ){
                if(nums[j] < nums[i]){
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}
