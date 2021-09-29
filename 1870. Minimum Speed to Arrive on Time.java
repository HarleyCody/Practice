//My Solution: Binary seach the speed, run the trains with every potential speed
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        if(dist.length - 1 >= hour) return -1;
        int left = 0, right = Integer.MAX_VALUE;
        while(left < right){
            int speed = (left + right) / 2;
            if(speed == 0) return 1;
            if(isArrived(dist, hour, speed)){
                right = speed;
            }else{
                left = speed + 1;
            }
        }
        return right;
    }
    
    private boolean isArrived(int[] dist, double hour, int speed){
        double time = 0;
        for(int i = 0; i < dist.length - 1; ++i){
            time += dist[i] % speed == 0? dist[i] / speed : dist[i] / speed + 1;
            if(time > hour) return false;
        }
        time += dist[dist.length - 1] / (double)speed;
        return time <= hour;
    }
}
