//My Solution: Using used and idle pq to store the server with specific order, every time assign the server until there is no server available 
//then increase the time
class Solution {
    class Server{
        int weight;
        int index;
        int endTime;
        public Server(int w, int i){
            weight = w;
            index = i;
        }
    }
    public int[] assignTasks(int[] servers, int[] tasks) {
        int[] ans = new int[tasks.length];

        PriorityQueue<Server> idleServers = new PriorityQueue<Server>((a, b) -> a.weight == b.weight? a.index - b.index : a.weight - b.weight);
        for(int i = 0; i < servers.length; ++i){
            idleServers.offer(new Server(servers[i], i));
        }
          
        PriorityQueue<Server> usedServers = new PriorityQueue<Server>((a, b) -> a.endTime - b.endTime);
        int curTime = 0;
        int taskIdx = 0;
        while(taskIdx < tasks.length){
            while(!usedServers.isEmpty() && usedServers.peek().endTime <= curTime){
                idleServers.offer(usedServers.poll());
            }
            if(idleServers.isEmpty()){
                curTime = usedServers.peek().endTime;
                continue;
            }
            while(taskIdx < tasks.length && taskIdx <= curTime && !idleServers.isEmpty()){
                Server curServer = idleServers.poll();
                ans[taskIdx] = curServer.index;
                curServer.endTime = curTime + tasks[taskIdx++];
                usedServers.offer(curServer);
            }
            ++curTime;
        }
        return ans;
    }
}
