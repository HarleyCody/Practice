class Solution {
    Random rand = new Random();
    int len;
    int[] og;
    int[] sufle;
    public Solution(int[] nums) {
        og = nums;
        len = nums.length;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        //System.arraycopy(og, 0, sufle, 0, og.length);
        return og;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(len == 0) return sufle;
        sufle = new int[len];
        System.arraycopy(og, 0, sufle, 0, len);
        int change;
        for(int i = 1; i < len; i++){
        // the prob of swap(i:i) = 1/i + 1;
        // the prob of swap(i:not i) = (1 - 1/i+1) / i;  == (i/i+1) / i = 1/i + 1
            change = rand.nextInt(i + 1);
            int tem = sufle[i];
            sufle[i] = sufle[change];
            sufle[change] = tem;
        }
        return sufle;
    }
}
