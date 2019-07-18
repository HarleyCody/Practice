class Solution {
    
    // combinatics use combination to calculate rlt;
    // formula c (all steps, steps for one direction)
    public int uniquePaths(int m, int n) {
        // all the steps should take;
        int steps = (m + n) - 2;
        // min steps can calculate less in later combinatics
        // pick min steps to go with a specific direction, rest of steps should go with another direction
        // Math.min(steps going left, steps going down)
        int cross = Math.min(m, n) - 1;
        int times = cross;
        // calculate mother, the last one is *1 does to perform so times - 1(times > 1 rather than > 0)
        // use long cuz it can be very large, int will bring loss;
        long mother = 1;
        while(times > 1){
            mother *= times--;
        }
        
        // calcualte son, from N(all) steps, N - 1 ... N - cross + 1(times = cross)
        // use long cuz it can be very large, int will bring loss;
        times = cross;
        long son = 1;
        while(times-- > 0){
            son *= steps--;
        }
        return (int)(son / mother);
    }
}
