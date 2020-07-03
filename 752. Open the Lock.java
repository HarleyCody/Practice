___________________________________________________________________________________Best Solution_______________________________________________________________________________
class Solution {
    // create a new class named as Lock
    // implement hashCode and Equals for Deadend set
    // implement compareTo to compare two locks
    // use PQ instead of LinkedList to faster unlock process
    private static class Lock implements Comparable<Lock> {
        int w1, w2, w3, w4;
        int turns;

        Lock(String initial) {
            char[] c = initial.toCharArray();
            w1 = c[0] - '0';
            w2 = c[1] - '0';
            w3 = c[2] - '0';
            w4 = c[3] - '0';
        }

        Lock(int w1, int w2, int w3, int w4, int turns) {
            this.w1 = (w1 + 10) % 10;
            this.w2 = (w2 + 10) % 10;
            this.w3 = (w3 + 10) % 10;
            this.w4 = (w4 + 10) % 10;
            this.turns = turns;
        }

        boolean isUnlocked() {
            return (w1 == 0) && (w2 == 0) && (w3 == 0) && (w4 == 0);
        }

        Set<Lock> makeTurn() {
            Set<Lock> nextOnes = new HashSet<Lock>();
            nextOnes.add(new Lock(w1 + 1, w2, w3, w4, turns+1));
            nextOnes.add(new Lock(w1 - 1, w2, w3, w4, turns+1));
            nextOnes.add(new Lock(w1, w2 + 1, w3, w4, turns+1));
            nextOnes.add(new Lock(w1, w2 - 1, w3, w4, turns+1));
            nextOnes.add(new Lock(w1, w2, w3 + 1, w4, turns+1));
            nextOnes.add(new Lock(w1, w2, w3 - 1, w4, turns+1));
            nextOnes.add(new Lock(w1, w2, w3, w4 + 1, turns+1));
            nextOnes.add(new Lock(w1, w2, w3, w4 - 1, turns+1));

            return nextOnes;
        }

        public int compareTo(Lock other) {
            int wx1 = Math.abs(w1 - 5) + Math.abs(w2 - 5) + Math.abs(w3 - 5) + Math.abs(w4 - 5);
            int wx2 = Math.abs(other.w1 - 5) + Math.abs(other.w2 - 5) + Math.abs(other.w3 - 5) + Math.abs(other.w4 - 5);
            return wx2 - wx1;
        }

        public int hashCode() {
            return w1 * 10 * 10 * 10 + w2 * 10 * 10 + w3 * 10 + w4;
        }

        public boolean equals(Object other) {
            if(this == other) {
                return true;
            }
            Lock obj = (Lock)other;
            return (w1 == obj.w1) && (w2 == obj.w2) && (w3 == obj.w3) && (w4 == obj.w4);
        }
    }

    public int openLock(String[] deadends, String target) {
        Lock initial = new Lock(target);
        Set<Lock> deads = new HashSet<>();
        for(String s: deadends) {
            deads.add(new Lock(s));
        }
        if(deads.contains(new Lock("0000")) || deads.contains(initial)) {
            return -1;
        }
        Queue<Lock> toProcess = new PriorityQueue<>();
        toProcess.add(initial);
        return openLock(toProcess, deads);
    }
    
    private int openLock(Queue<Lock> toProcess, Set<Lock> deadends) {
        Lock lock = null;
        while((lock = toProcess.poll()) != null) {
            if(deadends.contains(lock)) {
                continue;
            }
            if(lock.isUnlocked()) {
                return lock.turns;
            }
            for(Lock nextOne: lock.makeTurn()) {
                toProcess.add(nextOne);
            }
            deadends.add(lock);
        }
        return -1;
    }
}
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
