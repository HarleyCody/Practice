//My Solution: Binary Search to get the max
class Solution{
	public int maxLength(int[] ribbons, int k){
	    int left = 0;
	    int right = (int)1e5;
	    while(left < right){
            int mid = (left + right) / 2;
            if(canCut(ribbons, k, mid)){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return canCut(ribbons, k, left) ? left : left - 1;
    }

    private boolean canCut(int[] ribbons, int k, int length){
        if(length == 0) return true;
        int idx = 0;
        while(idx < ribbons.length && k > 0){
            k -= ribbons[idx++] / length;
        }
        return k <= 0;
    }
}
