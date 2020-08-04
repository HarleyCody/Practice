class Solution {
    
    // find peakId then find in left part and right part
    // find peak by comparing it with left val and right val
    // find left and right part by comparing its value, normal binary search
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();

        int l = 0, r = len - 1;
        int peakIdx = getPeak(mountainArr, 0 , len - 1);
        int ans = binarySearch(mountainArr, target, l, peakIdx, true);
        if(ans >= 0){
            return ans;
        }else{
            return binarySearch(mountainArr, target, peakIdx + 1, r, false);
        }
    }

    private int getPeak(MountainArray ma, int l, int r){
        int m = -1, vM, vL, vR;
        while(l < r){
            m = (l + r) / 2;
            vM = ma.get(m);
            vL = ma.get(m - 1);
            vR = ma.get(m + 1);

            if(vM > vL && vM > vR){
                break;
            }else if(vM < vL){
                r = m;
            }else if(vM < vR){
                l = m + 1;
            }
        }
        return m;
    }

    private int binarySearch(MountainArray ma, int tar, int left, int right, boolean isAscending){
        int m = -1, vM;
        int l = left, r = right;
        while(l < r){
            m = (l + r) / 2;
            vM = ma.get(m);
            if(isAscending){
                if(vM >= tar){
                    r = m;
                }else{
                    l = m + 1;
                }
            }else{
                if(vM > tar){
                    l = m + 1;
                }else{
                    r = m;
                }
            }
        }
        return isAscending ? (ma.get(r) == tar ? r : -1)  : ma.get(l) == tar ? l : -1;
    }
}
