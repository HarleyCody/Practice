
//Best Solution use array instead of set
class Solution {
    public boolean containsDuplicate(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int a:nums) {
            if(min > a) min = a;
            if(max < a)max = a;
        }

        boolean[] bool = new boolean[max-min+1];
        for (int value : nums) {
            if (bool[value - min]) return true;
            bool[value - min] = true;
        }
        return false;
    }
}
//My Solution: Use set
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> recorder = new HashSet<Integer>();
        for(int n : nums){
            if(!recorder.add(n)) return true;
        }
        return false;
    }
}
