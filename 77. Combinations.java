class Solution {
    // backtrack
    List<List<Integer>> ans = new ArrayList();
    // record changing list
    List<Integer> med = new ArrayList();
    
    public List<List<Integer>> combine(int n, int k) {
        if(k > n || k == 0) return ans;
        for(int i = 1; i <= n - k + 1; i++){
            // only search curtent one from start to n - k + 1, large than n - k + 1, it will not have enough elements later.
            med.add(i);
            if(med.size() == k){
                ans.add(new ArrayList(med));
            }
            else{
                // n - k + 2 and k make sure the last one is smaller than n
                helper(i + 1, n - k + 2, k);
            }
            med.remove((Integer)i);
        }
        return ans;
    }
    private void helper(int start, int n, int size){
        for(int i = start; i <= n; i++){
            // only search curtent one from start to n, large than n it will not have enough elements later.
            med.add(i);
            if(med.size() == size){
                ans.add(new ArrayList(med));
            }
            else{
                // n + 1 and size make sure the last one is smaller than OG: n
                    helper(i + 1, n + 1, size);
            }
            med.remove((Integer)i);
        }
    }
}77. Combinations
