___________________________________________________________________________________Best Solution______________________________________________________________________
// Core Idea; swap possibile pair. any swap can make permutation. all the swap combination conduct ans
// Question alter to: swap cur or not; if(chosen number(duplicate) has been swapped to current before, donot swap anymore)
// possible pair, chosen number is distinct among the number from cur and chosen number
class Solution {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        permute(nums, 0);
        
        return res;
    }
    
    public void permute(int[] nums, int start) {
        if (start == nums.length - 1) {
            List<Integer> sub = new ArrayList<Integer>();
            
            for (int i : nums) {
                sub.add(i);
            }
            res.add(sub);
            
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            if (i != start && !availableForPermute(nums, start, i)) continue; 
            
            swap(nums, start, i);
            permute(nums, start + 1);
            swap(nums, start, i);
        }
    }
    // prunning, if any number between cur and chosen is same as chosen number, do not swap here
    private boolean availableForPermute(int[] nums, int start, int chosen) {
        for (int i = start; i < chosen; i++) {
            if (nums[i] == nums[chosen]) {
                return false;
            }
        }
        
        return true;
    }
    
    private void swap(int[] nums, int s, int e) {
        int temp = nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }
}
____________________________________________________________________________________My Solution______________________________________________________________________
class Solution {
    int len;
    public List<List<Integer>> permuteUnique(int[] nums) {
        len = nums.length;
        HashSet<List<Integer>> ansSet = getList(nums, 0);
        
        return new ArrayList(ansSet);
    }
    
    private HashSet<List<Integer>> getList(int[] nums, int cur){
        HashSet<List<Integer>> ansSet = new HashSet();
        if(cur == len){
            ansSet.add(new LinkedList());
            return ansSet;
        }
        HashSet<List<Integer>> next = getList(nums, cur + 1);
        for(List<Integer> n : next){
            int size = n.size();
            for(int s = 0; s <= size; ++s){
                List<Integer> ansList = new LinkedList(n);
                ansList.add(s, nums[cur]);
                ansSet.add(ansList);
            }
        }
        return ansSet;
    }
}
