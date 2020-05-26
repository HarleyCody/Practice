________________________________________________________________Best Solution___________________________________________________________
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int num : nums) hs.add(num);
        
        if(nums.length == 0) return 0;
        
        int max = 1;
        
        for(int num : nums){
            // reduce to O(n) by only checking new head
            if(hs.contains(num - 1)) continue;
            int currMax = 1;
            while(hs.contains(num + 1)){
                num++;
                currMax++;
            }
            max = Math.max(currMax, max);
        }
    
        return max;
    }
}
_________________________________________________________________My Solution_____________________________________________________________
class Solution {
     // union find
     // union num with num + 1 and num - 1, root is smallest index(head of consecutive)
     // find by while loop
    HashMap<Integer, Integer> recorder = new HashMap();
    int[] root, lens;
    int ans = 1;
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if(len < 2){
            return len;
        }
        root = new int[len];
        lens = new int[len];
        for(int i = 0; i < len; ++i){
            root[i] = i;
            lens[i] = 1;
            recorder.put(nums[i], i);
        }
        
        for(int i = 0; i < len; ++i){
            int idx = recorder.get(nums[i]);
            if(idx != i){
                continue;
            }
            int left = recorder.getOrDefault(nums[i] - 1, -1);
            if(left != -1){
                union(left, idx);
            }
            int right = recorder.getOrDefault(nums[i] + 1, -1);
            if(right != -1){
                union(idx, right);
            }
        }
        return ans;
    }
    
    private void union(int x, int y){
        int px = find(x), py = find(y);
        if(px == py){
            return;
        }
        if(px < py){
            root[py] = px;
            lens[px] += lens[py];
            ans = Math.max(ans, lens[px]);
        }else{
            root[px] = py;
            lens[py] += lens[px];
            ans = Math.max(ans, lens[py]);
        }
    }
    
    private int find(int x){
        int px = root[x];
        while(x != px){
            x = px;
            px = root[x];
        }
        return px;
    }
}
