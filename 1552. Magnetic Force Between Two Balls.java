//Best Solution: Same idea, binary search to detect the maxDis
//Improve: Narrow down the right to be (max - min) / (m - 1)
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1;
        int right = (position[position.length - 1] - position[0])/(m - 1);
        while(left <= right){
            int mid = (left + right) / 2;
            if(useAllBalls(position, mid, m)){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    private boolean useAllBalls(int[] pos, int gap, int m){
        int curPos = pos[0];
        --m;
        for(int i = 1; i < pos.length; ++i){
            if(pos[i] >= gap + curPos){
                curPos = pos[i];
                --m;
            }
        }
        return m <= 0;
    }
}
//My Solution: try to use exact m balls to detect the maxDis
//If possible increae the dis otherwise, decrease;
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1;
        int right = position[position.length - 1];
        while(left <= right){
            int mid = (left + right) / 2;
            if(useAllBalls(position, mid, m)){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    private boolean useAllBalls(int[] pos, int gap, int m){
        int curPos = pos[0];
        --m;
        for(int i = 1; i < pos.length; ++i){
            if(pos[i] >= gap + curPos){
                curPos = pos[i];
                --m;
            }
        }
        return m <= 0;
    }
}
