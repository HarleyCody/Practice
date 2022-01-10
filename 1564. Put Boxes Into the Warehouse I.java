//Best Solution: Try to put boxes as much as possible, put boxes from large to small(from left to right)
class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);

        int bIdx = boxes.length - 1;
        int wIdx = 0;
        while(0 <= bIdx && wIdx < warehouse.length){
            if(boxes[bIdx] <= warehouse[wIdx]){
                ++wIdx;
            }
            --bIdx;
        }    
        return wIdx;
    }
}

//My Solution: Get the min array to know the limit of inserting box to warehosue[i] by O(1)
//              Insert from small to large
class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        for(int i = 1; i < warehouse.length; ++i){
            warehouse[i] = Math.min(warehouse[i - 1], warehouse[i]);
        }

        int bIdx = 0;
        int wIdx = warehouse.length - 1;
        while(bIdx < boxes.length && wIdx >= 0){
            if(boxes[bIdx] <= warehouse[wIdx]){
                ++bIdx;
            }
            --wIdx;
        }    
        return bIdx;
    }
}
