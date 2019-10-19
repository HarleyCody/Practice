_______________________________________________________Best Solution___________________________________________________________
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k > 0) return false;
        return canPartition(nums, k, new boolean[nums.length], 0, 0, sum / k);
    }
    
    private boolean canPartition(int[] nums, int k, boolean[] visited, int index, int sum, int target){
        if (k == 1) return true;
        // found a subset
        if (target == sum){
            return canPartition(nums, k - 1, visited, 0, 0, target);
        }
        if (sum > target) return false;
        // try every element and backtrack
        for (int i = index; i < nums.length; i++){
            if (visited[i]) continue;
            visited[i] = true;
            if (canPartition(nums, k, visited, i + 1, sum + nums[i], target)) return true;
            visited[i] = false;
        }
        return false;
    }
}
_________________________________________________________My Solution___________________________________________________________
class Solution {
    // recursion + backtrack
    // tar is sum for every subset
    int tar = 0;
    // times: number of subset
    int times = 0;
    // recorde used element
    HashSet<Integer> used = new HashSet();
    // when times = k - 1, return true; eg, when K = 4, only need to find three subset with sum tar.
    int K = 0;
    // adding, element in current subset.
    HashSet<Integer> adding = new HashSet();
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums.length == 0) return false;
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if((sum % k) != 0) return false;
        // get tar;
        tar = sum / k;
        K = k;
        // start recursion at 0;
        return can(nums, 0, tar);
    }
    private boolean can(int[] nums, int cur, int target){
        // find result;
        if(times == K - 1) return true;
        // all element are positive, so when tar < 0, previous element cannot be added. wrong combination 
        if(target < 0) return false;
        // find untill the last one, but cannot made a valid combination.
        if(cur == nums.length) return false;
        // cur is used, go to next element
        if(used.contains(cur)) {
            return can(nums, cur + 1, target);
        }
        // current subset add this element;
        adding.add(cur);
        // find a valid subset;
        if(target - nums[cur] == 0){
            // store for backtrack
            HashSet<Integer> tem = new HashSet(used);
            used.addAll(adding);
            times++;
            // rest cannot make k subset. current subset is not valid
            if(!can(nums, 0, tar)){
                // delete current subset; backtrack
                times--;
                used = tem;
            }else{
                return true;
            }
        }
        // adding cur, continue recursion
        if(!can(nums, cur + 1, target - nums[cur])){
            adding.remove(cur);
            return can(nums, cur + 1, target);
        }
        return true;
    }
}
