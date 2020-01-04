__________________________________________________________Best Solution________________________________________________________
class Solution {
    // use array instead of hashmap to recorder status(sum, index)
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] recorder = new int[2 * nums.length + 1];
        for( int i = 0; i < recorder.length; i++) recorder[i] = -2;
        int sum = n;
        int max = 0;
        recorder[n] = -1;
        for ( int i = 0; i < nums.length; i++){
            // 0 is -1, 1 is 1;
            sum += (nums[i] * 2 - 1);
            if (recorder[sum] == -2)
                recorder[sum] = i; 
            else
                max = Math.max(max, i - recorder[sum]);
        }
        return max;
    }
    
}
_________________________________________________________Basic Solution________________________________________________________
class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] p = new int[2*nums.length+1];
        for( int i = 0; i < p.length; i++) p[i] = -3;
        int diff = n;
        int max = 0;
        p[n] = -1;
        for ( int i = 0; i < nums.length; i++){
            diff += (nums[i]*2 - 1);
            // System.out.println(diff);
            if ( p[diff] == -3)
                p[diff] = i; 
            else
                max = Math.max(max, i - p[diff]);
        }
        
        // for(int k : p) {
        //     System.out.println(k);
        // }
        return max;
    }
    
}
