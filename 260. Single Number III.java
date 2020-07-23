______________________________________________________________________________Best Solution(bit)________________________________________________________________________
class Solution {
    // bit mask will only record bit difference between two numbers which only appear once
    // eg 3(011) 5 (101) bitmask (110)
    // lowbit represent the difference between these two numbers;
    // as lowbit, only one of ans number will contains this bit, other number contains this bit will be XOR by the other same one
    // find first by lowbit and the otherone is calculated by bitmask ^x to trade off x 
    public int[] singleNumber(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;
        
        System.out.println(bitmask);
        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);

        int x = 0;
        // bitmask which will contain only x, similar to start x at particular number and do XOR
        for (int num : nums) if ((num & diff) != 0) x ^= num;

        return new int[]{x, bitmask^x};
    }
}______________________________________________________________________________My Solution________________________________________________________________________
// hash set only record number appears once
class Solution {
    public int[] singleNumber(int[] nums) {
        HashSet<Integer> visited = new HashSet();
        for(int n : nums){
            if(!visited.add(n)){
                visited.remove(n);
            }
        }
        int[] ans = new int[visited.size()];
        int idx = 0;
        for(int n : visited){
            ans[idx++] = n;
        }
        return ans;
    }
}
