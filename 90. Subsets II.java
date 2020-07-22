____________________________________________________________________________My Solution iteration_______________________________________________________________________________
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums);
        int len = nums.length;
        
        List<List<Integer>> prev;
        int start = 0;
        for(int i = 0; i < len; ++i){
            prev = new ArrayList(ans.subList(start, ans.size()));
            start = ans.size();
            List<Integer> cur = new ArrayList();
            if(i == 0 || i > 0 && nums[i - 1] != nums[i]){
                prev = new ArrayList(ans);
                cur.add(nums[i]);
                ans.add(cur);
            }
            for(List<Integer> l : prev){
                cur = new ArrayList(l);
                cur.add(nums[i]);
                ans.add(cur);
            }
        }
        ans.add(new ArrayList());
        return ans;
    }
}
____________________________________________________________________________My Solution recursion_______________________________________________________________________________
class Solution {
    // add from prev to later one by one
    // recursion
    List<List<Integer>> ans = new ArrayList();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        addSubset(nums, 0, new ArrayList());
        ans.add(new ArrayList());
        return ans;
    }
    
    private void addSubset(int[] nums, int idx, List<List<Integer>> prev){
        if(idx == nums.length){
            return;
        }
        
        List<List<Integer>> src = new ArrayList(prev);
        List<Integer> cur = new ArrayList();
        int start = ans.size();
        if(idx == 0 || 0 < idx && nums[idx] != nums[idx - 1]){
            src = new ArrayList(ans);
            cur.add(nums[idx]);
            ans.add(cur);
        }
        
        for(List<Integer> l : src){
            cur = new ArrayList(l);
            cur.add(nums[idx]);
            ans.add(cur);
        }
        addSubset(nums, idx + 1, ans.subList(start, ans.size()));
    }
}
