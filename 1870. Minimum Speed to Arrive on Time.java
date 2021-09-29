//Best Solution: Narrow down the time range before doing the binary search
//Right bound = Max distance or Max distance / numbers after decimal

class Solution {
    
    int[] dist;
    int n;
    
    public int minSpeedOnTime(int[] dist, double hour) {
        this.dist = dist;
        n = dist.length;
        if (n - 1 > Math.ceil(hour - 1)) return -1;
        int l = 1;
        int r = r(hour);
        while (l < r) {
            int mid = (l + r) / 2;
            if (arrival(mid) <= hour) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    private int r(double hour) {
        int r = dist[0];
        for (int i : dist) r = Math.max(r, i);
        double afterDot = hour - Math.floor(hour);
        if (afterDot > 0) {
            r = Math.max(r, (int) Math.ceil(dist[n - 1] / afterDot));
        }
        return r;
    }
    
    private double arrival(int speed) {
        int time = 0;
        for (int i = 0; i < n - 1; i++) {
            // Improvement to around the int by reminder
            time += (dist[i] + speed - 1) / speed;
        }
        return ((double) dist[n - 1]) / speed + time;
    }
    
}
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
            time += (dist[i] + speed - 1)/ speed;
            if(time > hour) return false;
        }
        time += dist[dist.length - 1] / (double)speed;
        return time <= hour;
    }
}
