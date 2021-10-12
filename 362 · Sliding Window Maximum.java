//My Solution: Monotone queue, only record with ascending order. The farther the number is, the larger it should be.
public class Solution {
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList();
        int len = nums.length;
        Deque<Integer> q = new ArrayDeque();

        for(int i = 0; i < len; ++i){
            while(!q.isEmpty() && nums[i] > nums[q.peek()]){
                q.pollFirst();
            }
            q.offerFirst(i);
            if(i - q.peekLast() + 1 > k){
                q.pollLast();
            }
            if(i >= k - 1){
                ans.add(nums[q.peekLast()]);
            }
        }
        return ans;
    }
}
//My Solution: Sliding window, if maxIdx < left, use for loop to find max
//Every time move right try to update max
public class Solution {
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new LinkedList<Integer>();
        if(nums.length == 0) return ans;
        int maxIdx = -1;
        int curMax = Integer.MIN_VALUE;
        for(int i = 0; i < k; ++i){
            if(nums[i] >= curMax){
                curMax = nums[i];
                maxIdx = i;
            }
        }
        ans.add(curMax);
        int left = 0;
        int right = k - 1;
        while(++right < nums.length){
            if(curMax <= nums[right]){
                curMax = nums[right];
                maxIdx = right;
            }
            ++left;
            if(maxIdx < left){
                curMax = Integer.MIN_VALUE;
                for(int i = left; i <= right; ++i){
                    if(nums[i] >= curMax){
                        curMax = nums[i];
                        maxIdx = i;
                    }
                }
            }
            ans.add(curMax);
        }
        return ans;
    }
}
