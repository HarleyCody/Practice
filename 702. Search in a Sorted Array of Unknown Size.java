__________________________________________________________Best Solution____________________________________________________________________
class Solution {
    public int search(ArrayReader reader, int target) {
        int hi = 1;
        // search right boundary untill its bigger than target
        while (reader.get(hi) < target) {
            hi = hi << 1;
        }
        // previous hi is lower than target so binary seach
        int low = hi >> 1;
        while (low <= hi) {
            int mid = low+(hi-low)/2;
            if (reader.get(mid) > target) {
                hi = mid-1;
            } else if (reader.get(mid) < target) {
                low = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
___________________________________________________________My Solution____________________________________________________________________
class Solution {
// range is -9999 to 9999 and the numbers are unique
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 20000;
        while(left <= right){
            int mid = (right + left) /2;
            if(reader.get(mid) < target){
                left = mid + 1;
            }else if(reader.get(mid) > target){
                right = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
