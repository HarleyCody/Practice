// Best Solution: Diff implement sort manually(quicksort), only need to compare time instead of calcualting distance
class Solution {
   public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if(n <= 1) {
            return n;  
        }
        quickSort(position, speed, 0, n - 1);
        double max = -1;
        int counter = 0;
        for(int i  = n - 1; i >= 0; i--){
            double distance = target - position[i];
            double time = distance / speed[i];
            if(time > max){
                max = time;
                counter ++;
            }
        }
        return counter;
    }
    
    public void quickSort(int[] a, int[] b, int start, int end){
        if(start < end) {
            int pivot = partition(a, b, start, end);
            quickSort(a, b, start, pivot - 1);
            quickSort(a, b, pivot + 1, end);
        }
    }
    
    int partition(int[] a, int[] b, int start, int end) {

        int pivot = a[end];
        int smallestPosition = start -1;
        for(int i = start; i<end; i++) {
            if(a[i] <= pivot) {
                smallestPosition++;
                swap(a, i, smallestPosition);
                swap(b, i, smallestPosition);
            }
        }
        smallestPosition++;
        swap(a, end, smallestPosition);
        swap(b, end, smallestPosition);
        return smallestPosition;
    }
    
    void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

// My Solution: sort by distance, and check if two cars can meet before the previous car finish the trip.
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        int[][] tuple = new int[len][2];
        for(int i = 0; i < len; ++i){
            tuple[i][0] = target - position[i];
            tuple[i][1] = speed[i];
        }
        Arrays.sort(tuple, (a,b) -> a[0] - b[0]);
        
        int ans = 1, cur = 0;
        int pPos = tuple[0][0], pSpeed = tuple[0][1]; 
        int cPos = 0, cSpeed = 0;
        while(++cur < len){
            cPos = tuple[cur][0];
            cSpeed = tuple[cur][1];
            if(isFleet(pPos, pSpeed, cPos, cSpeed)) continue;
            
            pPos = cPos;
            pSpeed = cSpeed;
            ++ans;
        }
        return ans;
    }
    private boolean isFleet(int pp, int ps, int cp, int cs){
        if(ps >= cs) return false;
        double time = (cp - pp) / (double)(cs - ps);
        return pp - ps * time >= 0;
    }
}
