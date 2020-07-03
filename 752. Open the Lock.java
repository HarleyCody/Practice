___________________________________________________________________________________Best Solution_______________________________________________________________________________

____________________________________________________________________________________My Solution_______________________________________________________________________________
class Solution {
    // bfs find min steps;
    int[] dirs = {1, -1};
    HashSet<String> dead = new HashSet();
    public int openLock(String[] deadends, String target) {
        char[] init = {'0', '0', '0', '0'};
        String initStr = new String(init);
        if(target.equals("0000")){
            return 0;
        }

        for(String str : deadends){
            dead.add(str);
        }
        if(dead.contains("0000")){
            return -1;
        }
        
        Queue<char[]> q = new LinkedList();
        q.offer(init);
        
        int steps = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0; s < size; ++s){
                char[] cur = q.poll();
        
                for(int i = 0; i < 4; ++i){
                    char tmp = cur[i];
                    
                    for(int d : dirs){
                        if(d == -1 && cur[i] == '0'){
                            cur[i] = '9';
                        }else if(d == 1 && cur[i] == '9'){
                            cur[i] = '0';
                        }else{
                            cur[i]  += d; 
                        }
                        
                        String newLock = new String(cur);
                        if(!dead.add(newLock)){
                            cur[i] = tmp;
                            continue;
                        }

                        if(newLock.equals(target)){
                            return steps + 1;
                        }
                        q.offer(cur.clone());
                        cur[i] = tmp;
                    }
                }
            }
            ++steps;
        }
        return -1;
    }
}
