______________________________________________________________Best Solution______________________________________________________________
class Solution {
    // key do not remove element in pq unless have to
    // add in balanced manner,
    //1....k
    PriorityQueue<Integer> pq1;
    //k.....n
    PriorityQueue<Integer> pq2;
    // record i is in which pq 0: p1 1:pq2
    int[] tracker;
    // size of pq
    int[] count = new int[2];
    public double[] medianSlidingWindow(int[] nums, int k) {
        // left
        pq1 = new PriorityQueue<>(new Comparator<>(){
            public int compare(Integer a, Integer b){
                return nums[a] < nums[b] ? 1: -1;
            }
        });
        // right
        pq2 = new PriorityQueue<>(new Comparator<>(){
            public int compare(Integer a, Integer b){
                return nums[a] < nums[b] ? -1: 1;
            }
        });

        int n = nums.length;
        tracker = new int[n];
        double[] ans = new double[n-k+1];
        //initial status
        for(int i = 0 ;i < Math.min(nums.length, k);i++){
            addNum(nums, i, k);
        }
        int i = k;
        while(i <= n){
            ans[i-k] = getMedian(nums,i,k);
             if (i == n){
                break;
            }
            addAndUpdateQs(nums, i, k);
            i++;
        }
	  return ans;
    }
	
	double getMedian(int[] nums, int i, int k){
		if(count[0]  == count[1]){
			return ((long)nums[pq1.peek()] + nums[pq2.peek()])*1.0/2;
		}
		else if(count[0] < count[1]){
			return nums[pq2.peek()];
		}
		else{
			return nums[pq1.peek()];
		}
	}
	
	void addAndUpdateQs(int[] nums, int i, int k){
		count[tracker[i - k]]--;
		removeStale(i, k);
		addNum(nums, i, k);
		removeStale(i, k);
	}

	void addNum(int[] nums, int i, int k){
		if(count[1] == 0 && count[0] == 0){
			count[0]++;
			pq1.add(i);
            tracker[i] = 0;
        }
        else if(count[1] < count[0]){
            count[1]++;
            if(nums[pq1.peek()] > nums[i]){
                tracker[pq1.peek()] = 1;
                pq2.add(pq1.poll());
                
                pq1.add(i);
                tracker[i] = 0;
            }
            else{
                pq2.add(i);
                tracker[i] = 1;
            }
        }
        else if(count[0] < count[1]){
            count[0]++;
            if(nums[pq2.peek()] < nums[i]){
                tracker[pq2.peek()] = 0;
                pq1.add(pq2.poll());
                pq2.add(i);
                tracker[i] = 1;
            }
            else{
                pq1.add(i);
                tracker[i] = 0;
            }
        }
		else {
			if(nums[i] > nums[pq2.peek()]){
				pq2.add(i);
                tracker[i] = 1;
				count[1]++;
			}
			else {
				pq1.add(i);
                tracker[i] = 0;
				count[0]++;
			}
		}
	}
    
    // only store valid num in range of i - k,  i
	void removeStale(int i, int k){
		while(pq1.size() > 0 && pq1.peek() <= (i-k)){
			pq1.poll();
		}
		while(pq2.size() > 0 && pq2.peek() <= (i-k)){
			pq2.poll();
		}
	}
}
________________________________________________________________My Solution______________________________________________________________
// max heap & min heap
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        boolean isEven = k % 2 == 0;
        PriorityQueue<Integer> left = new PriorityQueue<Integer>((x, y) -> y == Integer.MIN_VALUE ? 
                                                                 -1 : x == Integer.MIN_VALUE ? 1 : y - x);
        PriorityQueue<Integer> right = new PriorityQueue();
        
        int s = 0, e = 0;
        while(e < k){
            left.offer(nums[e]);
            ++e;
        }
        while(left.size() > right.size() + 1){
            right.offer(left.poll());
        }
        int len = nums.length;
        double[] ans = new double[len - k + 1];
        if(k == 1){
            for(int i = 0; i < len; ++i){
                ans[i] = (double)nums[i];
            }
            return ans;
        }
        ans[0] = isEven ? ((long)left.peek() + (long)right.peek()) / 2.0 : left.peek();
        int idx = 1;
        while(e < len){
            if(!left.remove(nums[s])){
                right.remove(nums[s]);
            }
            left.offer(nums[e]);
            while(!right.isEmpty() && !left.isEmpty() && right.peek() <= left.peek()){
                right.offer(left.poll());
            }
            while(right.size() > left.size()){
                left.offer(right.poll());
            }
            while(left.size() > right.size() + 1){
                right.offer(left.poll());
            }
            ans[idx++] = isEven ? ((long)left.peek() + (long)right.peek()) / 2.0 : left.peek();
            ++s;
            ++e;
        }
        return ans;
    }
}
