____________________________Best Solution (System.arraycopy(int[]src, int start, int[] dst, int start, int end)__________________________________   
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if(len == 0) return;
        int steps = k % len;
        int start = len - steps;
        int[] tem = new int[nums.length];
        int index = 0;
        for(int i = start; i < len; i++){
            tem[index++] = nums[i];
        }
        for(int i = 0; i < start; i++){
            tem[index++] = nums[i];
        }
        System.arraycopy(tem, 0, nums, 0 , len);
    }
}
___________________________________________________Solution 2(for() copy from tem to num)____________________________________________________ 
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if(len == 0) return;
        int steps = k % len;
        int start = len - steps;
        int[] tem = new int[nums.length];
        int index = 0;
        for(int i = start; i < len; i++){
            tem[index++] = nums[i];
        }
        for(int i = 0; i < start; i++){
            tem[index++] = nums[i];
        }
        for(int i = 0 ; i < len; i++){
            nums[i] = tem[i];
        }
    }
}

_________________________________________________Solution 3(rewrite specific num iteratively when rewrite len times)______________________
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int newPos = k % len, og = nums[0];
        int times = 0;
        int start = 0;
        while(newPos != start || times < len){
            int tem = nums[newPos];  
            nums[newPos] = og;
            og = tem;
            times++;
            newPos += k;
            newPos %= len;
            if(newPos == start){
                nums[newPos] = og;
                times++;
                if(times < len){
                    start = ++newPos;
                    og = nums[start];
                    newPos += k;
                    newPos %= len;
                }
            }
        }
    }
}
