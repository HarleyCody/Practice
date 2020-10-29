_______________________________________________________________Best Solution___________________________________________________________
//Calcualte left, right and middle seperately
class Solution {
    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 0) {
            return 0;
        }
        
        int ans = 0; int k = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                k = 0;
            } else {
                k++;
                ans = Math.max(ans, (k + 1) / 2);
            }
        }
        
        // left
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                ans = Math.max(ans, i);
                break;
            }
        }
        
        // right
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                ans = Math.max(ans, seats.length - 1 - i);
                break;
            }
        }
        
        return ans;
    }
}
_______________________________________________________________My Solution_____________________________________________________________
//Cacualte the distance between two '1', in the left and right dis will be e - s;
//In the middle dis will be e - s / 2
class Solution {
    public int maxDistToClosest(int[] seats) {
        int len = seats.length;
        int ans = 0;
        int s = -1;
        
        int dis = 0;
        for(int i = 0; i < len; ++i){
            if(seats[i] == 1){
                dis = s == -1 ? i : (i - s) / 2;
                s = i;
            }
            ans = Math.max(ans, dis);
        }
        dis = len - 1 - s;
        
        return Math.max(ans, dis);
    }
}
