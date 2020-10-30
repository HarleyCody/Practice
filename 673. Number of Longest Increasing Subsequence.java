_______________________________________________________________Best Solution__________________________________________________________
// insert number one by one;
// binary search, find the pre longest increasing subsequence(LIS) for num[i];
// as the num is insert from left to right, so once found, it must be in the left of num[i];
// find correct position to insert into the list of (LIS) as binary search may only find minMax and maxMin for num[i]
// list(dp) record the last number of LIS and times(accumulated by same length in previous number)
// return the results in longest length;
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int size = nums.length;
        if (size == 0) return 0;
        if (size == 1) return 1;
        
        List<int[]>[] lists = (List<int[]>[]) new ArrayList[size + 1];
        lists[1] = new ArrayList<>();
        lists[1].add(new int[]{nums[0], 1});

        int len = 1;
        for (int i = 1; i < size; i++) {
            int n = nums[i];
            int l = 1, r = len + 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                List<int[]> curr = lists[mid];
                if (curr.get(curr.size() - 1)[0] >= n) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            
            if (l - 1 == len) {
                lists[++len] = new ArrayList<>();
            }  
            //System.out.println(l);
            int count = 0;
            if (l - 1 > 0) {
                List<int[]> pre = lists[l - 1];
                int hi = -1, lo = pre.size() - 1;
                while (lo > hi) {
                    int mi = lo + (hi - lo) / 2;
                    if (pre.get(mi)[0] >= n) {
                        hi = mi;
                    } else {
                        lo = mi - 1;
                    }                    
                }
                count = pre.get(pre.size() - 1)[1] - (lo >= 0 ? pre.get(lo)[1] : 0);
            } else count = 1;
            
            List<int[]> list = lists[l];
            count = count + (list.size() == 0 ? 0 : list.get(list.size() - 1)[1]);
            list.add(new int[]{n, count});
        }
    
        return lists[len].get(lists[len].size() - 1)[1];
    }
}
________________________________________________________________My Solution__________________________________________________________
// record time of longest increasing sbsequence ends at ith
// accumulate
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if(len == 0){
            return 0;
        }
        int[] lens = new int[len];
        int[] times = new int[len];
        int max = 1;
        for(int i = 0; i < len; ++i){
            lens[i] = 1;
            times[i] = 1;
            for(int j = i; 0 <= j; --j){
                if(nums[j] >= nums[i]){
                    continue;
                }
                if(lens[i] == lens[j] + 1){
                    times[i] += times[j];
                }else if(lens[j] + 1 > lens[i]){
                    lens[i] = Math.max(lens[i], lens[j] + 1);
                    times[i] = times[j];
                }
                max = Math.max(max, lens[i]);
            }
        }
        
        int ans = 0;
        for(int i = 0; i < len; ++i){
            if(max == lens[i]){
                ans += times[i];
            }
        }
        return ans;
    }
}
