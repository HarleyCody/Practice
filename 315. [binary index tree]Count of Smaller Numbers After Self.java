___________________________________________________________________________________Merge Sort________________________________________________________________
class Solution {
    // sort and find in the next part that all the num < num[l]
    // merge sort can do both sort and merge one times.
    // all the count in left comes from the right part of merge sort
    int[] count, indexes;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();     

        count = new int[nums.length];
        indexes = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            indexes[i] = i;
        }
        
        mergesort(nums, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
            res.add(count[i]);
        }
        return res;
    }
    private void mergesort(int[] nums, int start, int end){
        if(end <= start){
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, start, mid);
        mergesort(nums, mid + 1, end);

        merge(nums, start, end);
    }
    private void merge(int[] nums, int start, int end){
        int mid = (start + end) / 2;
        int l = start;
        int r = mid+1;
        int rightcount = 0;    	
        int[] new_indexes = new int[end - start + 1];
        
        // right is in the later part, so if num[r] < num[l], it should add num that l < r;
        int sort_index = 0;
        while(l <= mid && r <= end){
            // calcualte how many nums are less than l;
            if(nums[indexes[r]] < nums[indexes[l]]){
                // place r at sort_index
                new_indexes[sort_index] = indexes[r];
                ++rightcount;
                ++r;
            }else{
                // update count to l, as nums[r] >= nums[l] means the num in right later than r are all larger than nums[l];
                // count is accumlative, count from small range to big range, as only concentrate on the other half,
                new_indexes[sort_index] = indexes[l];
                count[indexes[l]] += rightcount;
                ++l;
            }
            ++sort_index;
        }
        while(l <= mid){
            new_indexes[sort_index] = indexes[l];
            count[indexes[l]] += rightcount;
            ++l;
            ++sort_index;
        }
        while(r <= end){
            new_indexes[++sort_index] = indexes[++r];
        }
        for(int i = start; i <= end; ++i){
            indexes[i] = new_indexes[i - start];
        }
    }  
}
___________________________________________________________________________________Improved Binary IndexTree_________________________________________________________________
class Solution {
    //improve use linkedlist instead of ArrayList
    //update num to all positive
    //query from idx - 1( -1 as do not include self), update from idx;
    class BinaryIndexTree{
        int[] tree;
        
        public BinaryIndexTree(int n){
            tree = new int[n];
        }
        
        private int lowbit(int x){
            return x & (-x);
        }
        
        public int query(int x){
            int sum = 0;
            while(0 < x){
                sum += tree[x];
                x -= lowbit(x);
            }
            return sum;
        }
        
        public void update(int x){
            while(x < tree.length){
                ++tree[x];
                x += lowbit(x);
            }
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        
        if(len == 0){
            return new ArrayList();
        }
        
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        
        for(int i = 0; i < len; ++i){
            min = Math.min(min, nums[i]);
        }
        
        for(int i = 0; i < len; ++i){
            nums[i] = nums[i] - min + 1;
            max = Math.max(max, nums[i]);
        }
        
        int cap = max + 1;
        
        BinaryIndexTree bit = new BinaryIndexTree(cap);
        
        List<Integer> ans = new LinkedList();
        for(int i = len - 1; 0 <= i; --i){
            int rlt = bit.query(nums[i] - 1);
            ans.add(0, rlt);
            bit.update(nums[i]);
        }
        
        return ans;
    }
}
___________________________________________________________________________________My Solution Binary IndexTree_________________________________________________________________
class Solution {
    // binary index tree, it records sum in unevenly distributed area,
    // lowbit use to git rid of lowest 1 bit for index, it points to the end of area
    // query and put;
    // index 0 cannot be used for data, so max - min + 2 make sure every record can be placed
    // update from top to bottom, so it will start from index and -= lowbit till 0
    // query from bottom to topm, it will start from cur area, and get every one in current area
    //      then it will add sum if previous area
    class BinaryIndexTree{
        int[] tree;
        
        public BinaryIndexTree(int n){
            tree = new int[n];
        }
        
        private int lowbit(int x){
            return x & (-x);
        }
        
        public int query(int x){
            int i = x;
            int sum = 0;
            while(0 < i){
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }
        
        public void update(int x){
            int i = x + 1;
            while(i < tree.length){
                ++tree[i];
                i += lowbit(i);
            }
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        
        for(int i = 0; i < len; ++i){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        
        int cap = max - min + 2;
        
        BinaryIndexTree bit = new BinaryIndexTree(cap);
        
        List<Integer> ans = new ArrayList();
        for(int i = len - 1; 0 <= i; --i){
            int rlt = bit.query(nums[i] - min + 1);
            ans.add(0, rlt);
            bit.update(nums[i] - min + 1);
        }
        
        return ans;
    }
}
