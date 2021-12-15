//Best Solution: Shrink from both sides, shrink priority depends on a >= 0 or not;
//      when a >= 0 sides are max value, so put result from left to right
//      when a < sides are min value, so put result from left to right
//      when a == 0, sides could be min and max, so put max in descending order or min in ascending order
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];
        int lo = 0;
        int hi = nums.length - 1;
        int idx = a >= 0 ? n - 1 : 0;
        while (lo <= hi) {
            int qa = quad(nums[lo], a, b, c);
            int qb = quad(nums[hi], a, b, c);
            if (a >= 0) {
                if (qa > qb) {
                    result[idx] = qa;
                    lo++;
                } else {
                    result[idx] = qb;
                    hi--;
                }
                idx--;
            } else {
                if (qa > qb) {
                    result[idx] = qb;
                    hi--;
                } else {
                    result[idx] = qa;
                    lo++;
                }
                idx++;
            }
        }
        return result;
    }
    private int quad(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
//My Solution: find symmetric axis and add result based on linear relation that related to a
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        double mid = (double)b / (-2 * a);
        double dis = Double.MAX_VALUE;
        
        int len = nums.length;
        int cIdx = 0;
        for(int i = 0; i < len; ++i){
            double curDis = (double)Math.abs(nums[i] - mid);
            if(curDis < dis){
                dis = curDis;
                cIdx = i;
            }
        }
        int[] ans = new int[len];
        if(a == 0){
            int start = 0;
            int offset = 1;
            if(b < 0){
                start = len - 1;
                offset = -1;
            }
            int idx = 0;
            for(int i = start; 0 <= i && i < len; i += offset, ++idx){
                ans[idx] = getNum(nums[i], a, b, c);
            }
        }else{
            int idx = 0;
            int offset = 1;
            if(a < 0){
                idx = len - 1;
                offset = -1;
            }
            ans[idx] = getNum(nums[cIdx], a, b, c);
            idx += offset;
            int l = cIdx - 1;
            int r = cIdx + 1;
            for(int i = idx; 0 <= i && i < len; i += offset){
                if(l == -1){
                    ans[i] = getNum(nums[r++], a, b, c);
                }else if(r == len){
                    ans[i] = getNum(nums[l--], a, b, c);
                }else if(Math.abs(nums[l] - mid) < Math.abs(nums[r] - mid)){
                    ans[i] = getNum(nums[l--], a, b, c);
                }else{
                    ans[i] = getNum(nums[r++], a, b, c);
                }
            }
        }
        return ans;
    }
    
    private int getNum(int x, int a, int b, int c){
        return a * x * x + b * x + c;
    }
}
