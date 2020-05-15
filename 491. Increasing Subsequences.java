_________________________________________________________________Best Solution___________________________________________________________
class Solution {
    //dfs to expand with cur and cur next
    // if cur == path[size - 1] might have overlap. stop here
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 2){
            return result;
        }
        dfs(nums, new ArrayList<>(), 0, result);
        return result;
    }
    private void dfs(int[] nums, ArrayList<Integer> path, int index, List<List<Integer>> result){
        if(index >= nums.length){
            if(path.size() >= 2){
                result.add(new ArrayList<>(path));
            }
            return;
        }
        // build up
        if(path.isEmpty() || nums[index] >= path.get(path.size()-1)){
            path.add(nums[index]);
            dfs(nums,path,index+1,result);
            path.remove(path.size()-1);
        }
        // skip == situation as it has been traversed find next new start
        if(!path.isEmpty() && nums[index] == path.get(path.size()-1)){
            System.out.println("break at" + index + " " + path);
            return;
        }
        // skip current one
        dfs(nums, path, index+1, result);
    }
}
___________________________________________________________________My Solution___________________________________________________________
class Solution {
    // build up from tail
    // mem[i] record ascending list from i to len;
    // mem[0] contains all ascending list and list with single num
    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums.length == 0){
            return new ArrayList();
        }

        int len = nums.length, m = 101;
        int[] min = new int[len + 1];
        for(int i = 0; i < len; ++i){
            min[i] = m;
            m = Math.min(nums[i], m);
        }
        
        HashSet<List<Integer>>[] mem = new HashSet[len];
        if(nums[len - 1] >= min[len - 1]){
            mem[len - 1] = new HashSet();
            mem[len - 1].add(Arrays.asList(nums[len - 1]));
        }
        List<Integer> med = new ArrayList();
        for(int i = len - 2; 0 <= i; --i){
            
            if(mem[i] == null){
                mem[i] = new HashSet();
            }
            if(min[i] <= nums[i]){    
                mem[i].add(Arrays.asList(nums[i]));
            }
            if(mem[i + 1] == null){
                continue;
            }
            for(List<Integer> back : mem[i + 1]){
                mem[i].add(new ArrayList(back));
                if(nums[i] <= back.get(0)){
                    med = new ArrayList(back);
                    med.add(0, nums[i]);
                    mem[i].add(new ArrayList(med));
                }
            }
        }
        List<List<Integer>> ans = new ArrayList();
        if(mem[0] == null){
            return ans;
        }
        for(List<Integer> l : mem[0]){
            if(l.size() != 1){
                ans.add(l);
            }
        }
        return ans;
    }
}
