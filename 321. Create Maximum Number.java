_________________________________________________________Best Solution___________________________________________________________________
class Solution {
// choose one by one
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int canSkip = nums1.length + nums2.length - k;
        return helper(nums1, 0, nums2, 0, k, canSkip, 1).result;
    }
    //最后一个参数ifSameThenWhich表示两者皆可选的情况下，选择哪个1表示选第一个数组，2表示选第二个数组。
    public ResultInfo helper(int[] nums1, int start1, int[] nums2, int start2, int k, int canSkip, int ifSameThenWhich) {
        int[] result = new int[k];
        for (int index = 0; index < k; index++) {
            int max1Index = maxWithSkip(nums1, start1, canSkip);
            int max2Index = maxWithSkip(nums2, start2, canSkip);
            //第一个数组的大，选第一个数组
            if (max1Index != -1 && max2Index != -1 && nums1[max1Index] > nums2[max2Index] || max1Index != -1 && max2Index == -1) {
                result[index] = nums1[max1Index];
                canSkip = canSkip - (max1Index - start1);
                start1 = max1Index + 1;
                
            //第二个数组的大，选第二个数组
            } else if (max1Index != -1 && max2Index != -1 && nums2[max2Index] > nums1[max1Index] || max2Index != -1 && max1Index == -1) {
                result[index] = nums2[max2Index];
                canSkip = canSkip - (max2Index - start2);
                start2 = max2Index + 1;
            //两值相等，则假设选1或选2后续值比较
            } else {
                boolean done = false;
                //还剩多少个没选
                int newK = k - index - 1;
                //假设选1则1新的start1为newStart1
                int newStart1 = max1Index + 1;
                int canSkip1 = canSkip - (max1Index - start1);
                //假设选1则1新的canSkip为canSkip1
                int newStart2 = max2Index + 1;
                //假设选2则2新的start2为newStart2
                int canSkip2 = canSkip - (max2Index - start2);
                //假设选2则2新的canSkip为canSkip2
                ResultInfo resultInfo1 = new ResultInfo(null, newStart1, start2, canSkip1);
                ResultInfo resultInfo2 = new ResultInfo(null, start1, newStart2, canSkip2);
                for (int tmpK = 1; tmpK <= newK; tmpK++) {//注意优化：每次只计算长度为1的数组，如果相同，再计算后面的
                    //假设选1
                    resultInfo1 = helper(nums1, resultInfo1.start1, nums2, resultInfo1.start2, 1, resultInfo1.canSkip, 1);
                    // improved
                    int result1 = resultInfo1.result[0];
                    //假设选2
                    resultInfo2 = helper(nums1, resultInfo2.start1, nums2, resultInfo2.start2, 1, resultInfo2.canSkip, 2);
                    int result2 = resultInfo2.result[0];
                    if (result1 > result2) {
                        //选1
                        result[index] = nums1[max1Index];
                        canSkip = canSkip - (max1Index - start1);
                        start1 = max1Index + 1;
                        done = true;
                        break;
                    } else if (result1 < result2) {
                        //选2
                        result[index] = nums2[max2Index];
                        canSkip = canSkip - (max2Index - start2);
                        start2 = max2Index + 1;
                        done = true;
                        break;
                    }
                }
                if (!done) {//都行，根据ifSameThenWhich选
                    if (ifSameThenWhich == 1) {
                        result[index] = nums1[max1Index];
                        canSkip = canSkip - (max1Index - start1);
                        start1 = max1Index + 1;
                    } else {
                        result[index] = nums2[max2Index];
                        canSkip = canSkip - (max2Index - start2);
                        start2 = max2Index + 1;
                    }
                }
            }
        }
        return new ResultInfo(result, start1, start2, canSkip);
    }
    //在满足canSkip条件下，找最大值。
    public int maxWithSkip(int[] nums, int start, int canSkip) {//返回最大值下标
        if (start == nums.length) {
            return -1;
        }
        int index = start;
        for (int i = 1; i <= canSkip; i++) {
            if (start + i < nums.length && nums[start + i] > nums[index]) {
                index = start + i;
            }
        }
        return index;
    }
}
class ResultInfo {//结果和结束时的状态值，其中状态值用于迭代优化
    int[] result;
    int start1;
    int start2;
    int canSkip;

    public ResultInfo(int[] result, int start1, int start2, int canSkip) {
        this.result = result;
        this.start1 = start1;
        this.start2 = start2;
        this.canSkip = canSkip;
    }
}
________________________________________________________Basic Solution___________________________________________________________________
// choose nums1 and nums2 to make maxArray1 with i digits and maxArray2 with k - i digits
// compare max1 and max2 starting from i and j
// merget if max1 > max2 choose max1[i] at ans[k] else max[2] at ans[k];
// compare ans make sure ans is greater 
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[k];
        //choose i digits from num1 and choose k - i digits from num2
        // m < k nums2 has less than k nums to choose, so k-m nums has to be choosed from num1;
        for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, ans, 0)) ans = candidate;
        }
        return ans;
    }
    // choose Max from num1 and num2 for every bit in k
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r)
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }
    
    // return true if nums1 > nums2, num1 from i, num2 from j;
    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        // 1 > 2 true;
        // num2 is shorter or num1 > num2
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    // choose k to make maxArray, with sequence
    public int[] maxArray(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[k];
        for (int i = 0, j = 0; i < n; ++i) {
            //n - i > k - j: rest num is more than needed number.
            // && curNumber is large than previous one, put cur to pre;
            while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) j--;
            if (j < k) ans[j++] = nums[i];
        }
        return ans;
    }
}
