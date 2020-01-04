____________________________________________________________Best Solution__________________________________________________________________
// segment tree or Indexed Binary Tree
public class NumArray {
    int[] tree;
    int[] nums;
    int size;
    
    
    public NumArray(int[] nums) {
        this.size = nums.length;
        this.nums = new int[size];
        this.tree = new int[size];
        for (int i = 0; i < size; ++i)
            update(i, nums[i]);
        
    }
    
    public void update(int i, int val) {
        int delta = val - nums[i];
        nums[i] = val;
        // update val from i
        for (; i < size; i = (i | i + 1))
            tree[i] += delta;
    }
    
    public int sumRange(int i, int j) {
        return sum(j) - sum(i - 1);
    }
    public int sum(int ind) {
        int ans = 0;
        //add previous val
        while (ind >= 0) {
            ans += tree[ind];
            ind &= ind + 1;
            ind--;
        }
        return ans;
    }
}


________________________________________________________My Solution______________________________________________________________________
// sum up nums, n[i] represents sum of the previous i - 1 nums(do not include nums[i])
class NumArray {
    int[] n;
    public NumArray(int[] nums) {
        n = new int[nums.length + 1];
        n[0] = 0;
        for(int i = 1; i < n.length; ++i){
            n[i] = nums[i - 1] + n[i - 1];
        }
    }
    
    public void update(int i, int val) {
        int og = n[i + 1] - n[i];
        int diff = val - og;
        for(int s = i + 1; s < n.length; ++s){
            n[s] += diff;
        }
    }
    
    public int sumRange(int i, int j) {
        return n[j + 1] - n[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
