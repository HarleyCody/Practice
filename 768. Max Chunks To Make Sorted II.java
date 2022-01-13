//My Solution: add chunk when prevMax <= lattMin.
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int[] min = new int[len];
        min[len - 1] = arr[len - 1];
        for(int i = len - 2; 0 <= i; --i){
            min[i] = Math.min(arr[i], min[i + 1]);
        }
        int ans = 0;
        int l = 0;
        int prevMax = 0;
        int lattMin = Integer.MAX_VALUE;
        while(l < len){
            prevMax = Math.max(arr[l], prevMax);
            lattMin = l == len - 1 ? Integer.MAX_VALUE : min[l + 1];
            if(prevMax <= lattMin){
                ++ans;
            }
            ++l;
        }
        return ans;
    }
}
