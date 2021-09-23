//Best Solution: Encapsulate enque time, process time and idx to an object
class Solution {
    class Task{
        int st;
        int pt;
        int idx;
        public Task(int startTime, int processTime, int index){
            st = startTime;
            pt = processTime;
            idx = index;
        }
    }
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        int[] ans = new int[len];
        
        Task[] taskArr = new Task[len];
        for(int i = 0; i < len; ++i){
            taskArr[i] = new Task(tasks[i][0], tasks[i][1], i);
        }
        Arrays.sort(taskArr, (a, b) -> a.st - b.st);
        PriorityQueue<Task> taskQ = new PriorityQueue<Task>((a, b) -> a.pt == b.pt ? a.idx - b.idx : a.pt - b.pt);
        
        int cur = 0, tail = 0;
        long curTime = 0;
        while(cur < len){
            while(tail < len && curTime >= taskArr[tail].st){
                taskQ.offer(taskArr[tail++]);
            }
            if(taskQ.isEmpty()){
                curTime = taskArr[tail].st;
                continue;
            }
            Task t = taskQ.poll();
            curTime += t.pt;
            ans[cur++] = t.idx;
        }
        return ans;
    }
}

//My Solution: Sort Array with enque time ascending; travel through array with a PQ
//Create a pq sorted by time and idx, so can pick up a correct task by poll;
class Solution {
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        int[] ans = new int[len];
        int[][] tasks3 = new int[len][3];
        
        for(int i = 0; i < len; ++i){
            tasks3[i][0] = tasks[i][0];
            tasks3[i][1] = tasks[i][1];
            tasks3[i][2] = i;
        }
        Arrays.sort(tasks3, (a, b) -> a[0] == b[0]? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> taskQ = new PriorityQueue<int[]>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        
        int cur = 0, tail = 0;
        long curTime = 0;
        while(cur < len){
            if(!taskQ.isEmpty()){
                int[] task = taskQ.poll();
                curTime += task[1];
                ans[cur++] = task[2];
            }
            if(tail < len && taskQ.isEmpty() && curTime < tasks3[tail][0]){
                curTime = tasks3[tail][0];
            }
            while(tail < len && curTime >= tasks3[tail][0]){
                taskQ.offer(tasks3[tail++]);
            }
        }
        
        return ans;
    }
}
