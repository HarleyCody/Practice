___________________________________________________________Fastest Solution_______________________________________________________________
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        f(lists, new ArrayList<>(), nums, 0);
        return lists;
    }
    
    public void f(List<List<Integer>> lists, List<Integer> list, int[] nums, int idx) {
        // add list from last layer to ans;
        lists.add(new ArrayList<>(list));
        for(int i = idx; i < nums.length; i++) {
            // add current num to list
            list.add(nums[i]);
            // start from next num continue to add;
            f(lists, list, nums, i + 1);
            // backtrace list to original status in current layer; as list only add one element to be a new list, it only remove last element to go back
            list.remove(list.size() - 1);
        }
    }
}

______________________________________________________________My Solution__________________________________________________________________
class Solution {
    List<List<Integer>> ans = new LinkedList();
    public List<List<Integer>> subsets(int[] nums){
        ans.add(new LinkedList());
        subsets(nums, 0);
        return ans;
    }
    // similar to dp
    public void subsets(int[] nums, int s){
        List<Integer> med = new LinkedList();
        if(s == nums.length) return;
        // initialize ans in nums[0];
        if(s == 0){
            med.add(nums[0]);
            ans.add(med);
        }
        // ans of nums[i + 1] = every list in ans of nums[i] add(nums[i + 1]) and put added list into ans;
        else if(s < nums.length){
            int size = ans.size();
            for(int i = 0; i < size; ++i){
                med = new LinkedList(ans.get(i));
                //add current num;
                med.add(nums[s]);
                //add new list;
                ans.add(med);
            }
        }
        //find ans in nums[s + 1]
        subsets(nums, ++s);
        return;
    }
}
