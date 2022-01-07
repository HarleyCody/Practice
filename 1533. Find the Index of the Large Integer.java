//My Solution: Binary search left half and right half if there is odd number of values like 0 1 2 search 01 and 1 2
// 1, 2, 3, 4 mid = 2
// 0, 1 mid = 0
// r - l == odd even number in range; compare l mid, mid + 1, right;
//              odd number in range: compare l mid, mid + 1 right;
class Solution {
    public int getIndex(ArrayReader reader) {
        int l = 0;
        int r = reader.length() - 1;
        while(l < r){
            int x = (l + r) >> 1;
            int y = x + (r - l) % 2;
            if(reader.compareSub(l, x, y, r) < 0){
                l = y;
            }else{
                r = x;
            }
        }
        
        return r;
    }
}
