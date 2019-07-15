/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {

        int start = 1, end = n;
        while(start < end){
            // append by (end - start)/2 to avoid stackoverflow; (start + end) / 2 will cause overflow
            int mid = start + (end - start) / 2;
            // mid is true, not a bad version, start from mid + 1
            if(!isBadVersion(mid)) start = mid + 1;
            // mid is bad version, search from previous, try to minimize ans;
            else end = mid;
        }
        return start;
    }
}
