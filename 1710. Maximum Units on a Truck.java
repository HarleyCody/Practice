// Best Solution: use constant space to sum up the number of box for specific type.
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[] recorder = new int[1001];
        for(int[] boxType : boxTypes){
            recorder[boxType[1]] += boxType[0];
        }
        
        int ans = 0;
        int idx = 1000;
        int use = 0;
        while(0 <= idx && truckSize > 0){
            if(recorder[idx] > 0){
                use = Math.min(truckSize, recorder[idx]);
                ans += use * idx;
                truckSize -= use;
            }
            --idx;
        }
        return ans;
    }
}
//My Solution: Greedy, get box that has more units first
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int ans = 0;
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int idx = 0;
        while(idx < boxTypes.length && truckSize > 0){
            ans += boxTypes[idx][0] * boxTypes[idx][1];
            truckSize -= boxTypes[idx++][0];
        }
        if(truckSize > 0) return ans;
        truckSize = 0 - truckSize;
        ans -= boxTypes[--idx][1] * truckSize;
        return ans;
    }
}
