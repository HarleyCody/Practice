//Best Solution: Same idea, but improved in is Feasible (line 29). int will never exhaust double, so donot need compare to offset 1 to delete the extra station usage
class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        int len = stations.length;
        double[] distances = new double[len-1];
        double l = 0, r = 0;
        for (int i=1;i<len;i++) {
            distances[i-1] = (double) (stations[i] - stations[i-1]);
            r = Math.max(r, distances[i-1]);
        }
        
        while (r - l > 1e-6) {
            double mid = (r + l) / 2;
            if (!isFeasible(distances, mid, k)) l = mid;
            else r = mid;
        }
        
        return l;
    }
    
    private boolean isFeasible(double[] distances, double maxDis, int k) {
        return minNumS(distances, maxDis) <= k;
    }
    
    private int minNumS(double[] distances, double maxDis) {
        int len = distances.length;
        int numOfS = 0;
        for (int i=0;i<len;i++) {
            numOfS += (int) (distances[i] / maxDis);
        }
        return numOfS;
    }
}

//My Solution: Binary search to minimize the distance with trying to use up all k;
class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        int len = stations.length;
        int[] distance = new int[len - 1];
        double minDistance = 0;
        double maxDistance = Double.MIN_VALUE;
        for(int i = 1; i < len; ++i){
            distance[i - 1] = stations[i] - stations[i - 1];
            maxDistance = Math.max(maxDistance, distance[i - 1]);
        }
        double increment = 5 * 1e-6;
        while(minDistance < maxDistance){
            double midDistance = (minDistance + maxDistance) / 2;
            if(canBuild(distance, midDistance, k)){
                maxDistance = midDistance - increment;
            }else{
                minDistance = midDistance + increment; 
            }
        }
        return maxDistance;
    }
    
    private boolean canBuild(int[] distance, double target, int k){
        int len = distance.length;
        int i = 0;
        while(k >= 0 && i < len){
            double num = distance[i] / target;
            num += num == (int)num ? -1 : 0;
            k -= (int)num;
            ++i;
        }
        return k >= 0;
    }
}
