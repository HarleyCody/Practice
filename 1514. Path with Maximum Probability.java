//Best Solution: dp with Dijstra, only do next level when there is new possiblity calculated
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] ret=new double[n];
        ret[start]=1;
        boolean flag;
        for(int i=0;i<n;++i){
            flag = false;
            for(int j=0;j<edges.length;++j)
            {
                int[] e=edges[j];
                if(ret[e[1]]<ret[e[0]]*succProb[j]){
                    flag = true;
                    ret[e[1]]=ret[e[0]]*succProb[j]; 
                }
                else if(ret[e[0]]<ret[e[1]]*succProb[j]){
                    flag = true;
                    ret[e[0]]=ret[e[1]]*succProb[j];
                }
            }
            if(!flag)
                break;
        }
        return ret[end];
    }  
}
//My Solution: Dijstra to find the max prob. 
//prob is the weight for Dijstra and PriorityQueue to get the max prob
//check duplicate must be out of for loop
class Solution {
    class Status{
        int idx;
        double prob;
        public Status(int i, double p){
            idx = i;
            prob = p;
        }
    }
    List<Status>[] to;
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        to = new ArrayList[n];
        for(int i = 0; i < edges.length; ++i){
            int f = edges[i][0];
            int t = edges[i][1];
            if(to[f] == null){
                to[f] = new ArrayList<Status>();
            }
            to[f].add(new Status(t, succProb[i]));
                
            if(to[t] == null){
                to[t] = new ArrayList<Status>();
            }
            to[t].add(new Status(f, succProb[i]));
        }
        boolean[] visited = new boolean[n];
        PriorityQueue<Status> q = new PriorityQueue<Status>((a, b) -> Double.compare(b.prob, a.prob));
        q.offer(new Status(start, 1.0));
        
        double ans = 0;
        while(!q.isEmpty()){
            Status curStatus = q.poll();
            if(curStatus.idx == end) return curStatus.prob;
            if(visited[curStatus.idx] || to[curStatus.idx] == null) continue;
            visited[curStatus.idx] = true;
            for(Status s : to[curStatus.idx]){
                Status nStatus = new Status(s.idx, curStatus.prob * s.prob);
                q.offer(nStatus);
            }
        }
        
        return 0;
    }
}
