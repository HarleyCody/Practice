class Solution {
    // sort from tail to head, find exact number in pos[i]
    // reverse to head, reverse to tail, search --tar
    // stops at tar == 0 return empty list
    public List<Integer> pancakeSort(int[] A) {
        int tar = A.length;
        return pancakeSort(A, tar);
    }
    
    private List<Integer> pancakeSort(int[] A, int tar){
        if(tar == 0) return new ArrayList();
        
        List<Integer> ans = new ArrayList();
        if(tar != A[tar - 1]){
            int i = 0;
            while(i < tar - 1 && A[i] != tar){
                ++i;
            }
            ans.add(i + 1);
            reverse(A, i);
            ans.add(tar);
            reverse(A, tar - 1);
        }
        ans.addAll(pancakeSort(A, --tar));
        return ans;
    }
    
    private void reverse(int[] nums, int end){
        int l = 0, r = end;
        while(l < r){
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
        }
    }
}
