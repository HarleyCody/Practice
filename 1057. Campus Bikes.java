__________________________________________________________Best Solution____________________________________________________________________
class Solution {
    // find a inner loop with minimal distance with all points(bikes and worker)
    // recorder x, y and index;
    class Point{
        int x;
        int y;
        int i;
        public Point(int i, int[][] point){
            this.x = point[i][0];
            this.y = point[i][1];
            this.i = i;
        }
    }
    // find valid pair( bike and worker are both clest point to each other)
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] ans = new int[workers.length];
        // HashSet reduce time complexity
        HashSet<Point> wSet = new HashSet();
        HashSet<Point> bSet = new HashSet();
        // Array to point
        for(int i = 0; i < workers.length; i++){
            Point pWorker = new Point(i, workers);
            wSet.add(pWorker);
        }
        for(int i = 0; i < bikes.length; i++){
            Point pBike = new Point(i, bikes);
            bSet.add(pBike);
        }
        // find for every worker
        while(!wSet.isEmpty()){
            Point curWorker = wSet.iterator().next();
            Point bike = null, worker = null;
            
            // continue until find a inner loop
            while(curWorker != null){
                // cloest bike to curWorker
                bike = cloest(curWorker, bSet);
                // cloest worker to bike
                worker = cloest(bike, wSet);
                // bike and curWorker are colest point to each other
                if(curWorker == worker){
                    break;
                }else{// continue find.
                    curWorker = worker;
                }
            }
            // put loop to ans
            ans[curWorker.i] = bike.i;
            // removed used records
            wSet.remove(curWorker);
            bSet.remove(bike);
        }
        return ans;
    }
    // find cloest point in tarSet for tar Point
    private Point cloest(Point tar, Set<Point> tarSet){
        int dis = Integer.MAX_VALUE;
        Point ans = null;
        for(Point point : tarSet){
            int curDis = Math.abs(tar.x - point.x) + Math.abs(tar.y - point.y);
            if(dis > curDis){
                ans = point;
                dis = curDis;
            }else if(dis == curDis){ // same distance select point with smaller index;
                ans = ans.i < point.i ? ans : point;
            }
        }
        return ans;
    }
}
__________________________________________________________My Solution______________________________________________________________________
class Solution {
    // get cloest pair for workers.length times
    int[] ans;
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        ans = new int[workers.length];
        for(int i = 0; i < workers.length; i++){
            getValidPair(workers, bikes);
        }
        return ans;
    }
    // get cloest pair(worker and bike) in current time
    private void getValidPair(int[][] workers, int[][] bikes){
        int dis = Integer.MAX_VALUE;
        int indexOfBike = 0;
        int indexOfWorker = 0;
        for(int i = 0; i < workers.length; i++){
            if(workers[i][0] == -1) continue;
            for(int j = 0; j < bikes.length; j++){
                if(bikes[j][0] == -1) continue;
                int curDis = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if(dis > curDis){
                    dis = curDis;
                    indexOfBike = j;
                    indexOfWorker = i;
                }
            }
        }
        ans[indexOfWorker] = indexOfBike;
        workers[indexOfWorker][0] = -1;
        bikes[indexOfBike][0] = -1;
    }
}
