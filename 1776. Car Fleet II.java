//Best Solution: Same idea but use array to simulate stack
class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] result = new double[n];

        int[] stack = new int[n];
        int stackSize = 0;
        for (int i = n-1; i >= 0; i--) {
            int currSpeed = cars[i][1];
            int currPos = cars[i][0];
            while (stackSize > 0) {
                int otherCar = stack[stackSize - 1], otherSpeed = cars[otherCar][1], otherPos = cars[otherCar][0];
                if (otherSpeed < currSpeed) {
                    double collisionTime = (otherPos - currPos) / (double) (currSpeed - otherSpeed);

                    if (collisionTime <= result[otherCar] || result[otherCar] == -1.0) {
                        result[i] = collisionTime;
                        break;
                    }
                }
                stackSize--;
            }
            if (stackSize == 0) result[i] = -1.0;
            stack[stackSize++] = i;
        }
        return result;   
    }
}
//Stack: push every car into stack and check previous car for current car
//Find answer only when current car is faster and crush time is less than previous car or previous car is not crushed
class Solution {
    public double[] getCollisionTimes(int[][] cars) {
        int len = cars.length;
        double[] ans = new double[len];
        Arrays.fill(ans, -1.0);
        
        Stack<Integer> sta = new Stack<Integer>();
        for(int i = len - 1; 0 <= i; --i){
            int cPos = cars[i][0];
            int cSpeed = cars[i][1];
            while(!sta.isEmpty()){
                int pPos = cars[sta.peek()][0];
                int pSpeed = cars[sta.peek()][1];
                if(pSpeed < cSpeed){
                    double crushTime = (pPos - cPos) / (double)(cSpeed - pSpeed);
                    if(crushTime <= ans[sta.peek()] || ans[sta.peek()] == -1.0){
                        ans[i] = crushTime;
                        break;
                    }
                }
                sta.pop();
            }
            sta.push(i);
        }
        
        return ans;
    }
}
