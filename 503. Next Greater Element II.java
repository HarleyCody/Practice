_______________________________________________________Best Solution__________________________________________________________
class Solution {
    // Ideas; find the next greater num, in ascending order, use res to reocrd sequence of ascending;
    public int[] nextGreaterElements(int[] nums) {
        if(nums.length==0)return new int[0];
        int[] res = new int[nums.length];
        // res record the index of next greater num;
        res[nums.length - 1] = -1;
        // from tail to head, assure seraching in the tail part is ascending as it goes back to head, res will record the index of ascending sequence of.
        
        // eg 2 1 3, if i = 0, find next is 1;
        // 1 < 2 then find next greater of 1(i.e, 3) so next greater of 2 is 3;
        for(int i = nums.length - 2; i >= 0; i--){
            int k = i + 1;
            while(nums[i] >= nums[k] && res[k] != -1){
                k = res[k];
            }
            // from tail to head, get the first valid nums[k]
            if(nums[k] > nums[i])
                // valid k is greater than i, record index;
                res[i] = k;
            else
                res[i] = -1;
        }
        // next greater is before i;
        for(int i = nums.length - 1; i >= 0; i--){
            // when i = nums.length - 1; it connect k(nums.length) with nums[0]; the rest is same as before 
            int k = (i + 1) % nums.length;
            if(res[i] != -1)continue;
            
            while(nums[i] >= nums[k] && res[k]!=-1){
                k = res[k];
            }
            if(nums[k] > nums[i])res[i] = k;
            else
                res[i]=-1;
        }
        for(int i=0;i<nums.length;i++){
            if(res[i]!=-1)
            res[i] = nums[res[i]];
        }
        return res;
    }
}
________________________________________________________Stack Solution_________________________________________________________
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n]; 
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }   
        return next;
    }
}
_______________________________________________________My Solution____________________________________________________________
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums.length == 0) return new int[0];
        int size = nums.length;
        int[] ans = new int[size];
        for(int i = 0; i < nums.length; ++i){
            int j = i + 1;
            boolean found = false;
            // from i + 1 to tail
            while(j < size){
                if(nums[i] < nums[j]){
                    ans[i] = nums[j];
                    found = true;
                    break;
                }
                ++j;
            }
            if(found) continue;
            // find from head to i
            j = 0;
            while(j < i){
                if(nums[i] < nums[j]){
                    ans[i] = nums[j];
                    found = true;
                    break;
                }
                ++j;
            }
            if(found) continue;
            // not found
            ans[i] = -1;
        }
        return ans;
    }
}
