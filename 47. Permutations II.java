___________________________________________________________________________________Best Solution______________________________________________________________________
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
