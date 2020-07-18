________________________________________________________________________________Best Solution______________________________________________________________________________________
class Solution {
    /* There are only 4 cards and only 4 operations that can be performed.
    Even when all operations do not commute, that gives us an upper bound of 12 * 6 * 2 * 4 * 4 * 4 = 921612∗6∗2∗4∗4∗4=9216 possibilities,
    which makes it feasible to just try them all. Specifically, we choose two numbers (with order) in 12 ways and perform one of 4 operations(12 * 4).
    Then, with 3 remaining numbers, we choose 2 of them and perform one of 4 operations (6 * 4).
    Finally we have two numbers left and make a final choice of 2 * 4 possibilities.*/
    
    public boolean judgePoint24(int[] nums) {
        ArrayList A = new ArrayList<Double>();
        for (int v: nums) A.add((double) v);
        return solve(A);
    }

    private boolean solve(ArrayList<Double> nums) {
        if (nums.size() == 0) return false;
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j){
                    continue;
                }
                
                ArrayList<Double> nums2 = new ArrayList<Double>();
                //add rest num to Calculate
                for (int k = 0; k < nums.size(); k++){
                    if (k != i && k != j) {
                        nums2.add(nums.get(k));
                    }
                }
                
                // four operations k is in different meaning here
                for (int k = 0; k < 4; k++) {
                    //pruning, as k < 2 only performs + and *, and i + j == j + i, i * j == j * i.
                    //just skip i < j for redundancy
                    if (k < 2 && j > i) continue;
                    if (k == 0) nums2.add(nums.get(i) + nums.get(j));
                    if (k == 1) nums2.add(nums.get(i) * nums.get(j));
                    if (k == 2) nums2.add(nums.get(i) - nums.get(j));
                    if (k == 3) {
                        if (nums.get(j) != 0) {
                            nums2.add(nums.get(i) / nums.get(j));
                        } else {
                            continue;
                        }
                    }
                    if (solve(nums2)) return true;
                    nums2.remove(nums2.size() - 1);
                }
            }
        }
        return false;
    }
}
________________________________________________________________________________My Solution______________________________________________________________________________________
class Solution {
    //generate input to detect answer
    //detect answer by compute(nums, s, e); recursively
    //combine left and right
    public boolean judgePoint24(int[] nums) {
        Set<int[]> inputs = new HashSet();
        for(int i = 0; i < 4; ++i){
            int[] input = new int[4];
            input[0] = nums[i];
            for(int j = 0; j < 4; ++j){
                if(j == i){
                    continue;
                }
                input[1] = nums[j];
                for(int k = 0; k < 4; ++k){
                    if(k == i || k == j){
                        continue;
                    }
                    input[2] = nums[k];
                    input[3] = nums[6 - k - i - j];

                    if(compute(input, 0, 4).contains(24.0) || compute(input, 0, 4).contains(23.99999999999999)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private Set<Double> compute(int[] nums, int s, int e){
        Set<Double> ans = new HashSet();
        if(s == e - 1){
            ans.add((double)nums[s]);
            return ans;
        }
        
        for(int i = s + 1; i < e; ++i){
            Set<Double> left = compute(nums, s, i);
            Set<Double> right = compute(nums, i, e);
            for(double l : left){
                for(double r : right){
                    ans.add(l + r);
                    ans.add(l * r);
                    ans.add(l - r);
                    ans.add(r - l);
                    if(r != 0){
                        ans.add(l / r);
                    }
                    if(l != 0){
                        ans.add(r / l);
                    }
                }
            }
        }
        return ans;
    }
}
