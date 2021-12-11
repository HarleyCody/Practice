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
