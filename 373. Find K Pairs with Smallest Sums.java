__________________________________________________________Best Solution_________________________________________________________________
class Solution {
    // store header no(nums1[i], nums2[0]) in pq with sum ascending.
    // retrieve node from pq, if current node fit, add next node(nums1[i], nums2[j + 1]) to pq;
    // new node will also be sorted by pq.
    // similar to bfs, but with pq;
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return result;
        }
        
        //sort based on sum
        Queue<SumInfo> queue =
            new PriorityQueue<>((a1, a2) -> Integer.compare(a1.a + a1.b, a2.a + a2.b));
        
        for(int i = 0; i < nums1.length; i++) {
            SumInfo sumInfo = new SumInfo(nums1[i], nums2[0], 0);
            queue.offer(sumInfo);
        }
        
        //注意K要先--，不然被continue带走了
        while(k-- > 0 && !queue.isEmpty()) {
            SumInfo sumInfo = queue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(sumInfo.a);
            list.add(sumInfo.b);
            result.add(list);
            
            //最后一位了 没有可以添加的了， 看看queue里剩下的哪些满足吧  
            if(sumInfo.bIndex == nums2.length - 1) {
                continue;
            }
            
            sumInfo.b = nums2[sumInfo.bIndex + 1];
            sumInfo.bIndex = sumInfo.bIndex + 1;
            queue.offer(sumInfo);
        }
        
        return result;
    }
}

//我需要有一个index来知道我nums2取到哪一个了
class SumInfo {
    int a;
    int b;
    int bIndex;
    SumInfo(int a, int b, int bIndex) {
        this.a = a;
        this.b = b;
        this.bIndex = bIndex;
    }
}
___________________________________________________________My Solution__________________________________________________________________
class Solution {
    // pq narrow down the ans, transfer ans from int[] to List<List>
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if(nums1.length == 0 || nums2.length == 0 || k == 0){
            return new ArrayList();
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x , y) -> y[0] + y[1] - x[0] - x[1]);
        int max = 0, size = 0;
        for(int n1 : nums1){
            for(int n2 : nums2){
                if(size == k){
                    int curVal = n1 + n2;
                    int peekVal = pq.peek()[0] + pq.peek()[1];
                    
                    if(curVal >= peekVal) break;
    
                    pq.offer(new int[]{n1, n2});
                    pq.poll();
                }else{
                    pq.offer(new int[]{n1, n2});
                    ++size;
                }
            }
        }
        List<List<Integer>> ans = new ArrayList();
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            ans.add(Arrays.asList(cur[0], cur[1]));
        }
        return ans;
    }
}
