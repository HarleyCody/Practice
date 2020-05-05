______________________________________________________________Best Solution_____________________________________________________________
class ExamRoom {
    // interval record length of blocks
    // seat will choose a seat in maxInterval, and and prev block and back block
    // when it leave, it will get prev block and back block then connect them together
    // if prev is null the new block starts from p, if end is null the new block end at p
    class Interval {
        int start;
        int end;
        int length;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            if (start == 0 || end == N - 1) {
                this.length = end - start;
            } else {
                this.length = (end - start) / 2;
            }
        }
    }
    private PriorityQueue<Interval> pq;
    private int N;
    
    public ExamRoom(int N) {
        this.pq = new PriorityQueue<>((a, b) -> a.length != b.length ? b.length - a.length : a.start - b.start);
        this.N = N;
        pq.offer(new Interval(0, N - 1));
    }
    
    public int seat() {
        Interval in = pq.poll();
        int result;
        if (in.start == 0) {
            result = 0;
        } else if (in.end == N - 1) {
            result = N - 1;
        } else {
            result = in.start + in.length;
        }
        
        if (result > in.start) {
            pq.offer(new Interval(in.start, result - 1));   
        }
        if (in.end > result) {
            pq.offer(new Interval(result + 1, in.end));   
        }
        return result;
    }
    
    public void leave(int p) {
        Interval prev = null;
        Interval next = null;
        for (Interval in: pq) {
            if (in.end + 1 == p) {
                prev = in;
            }
            if (in.start - 1 == p) {
                next = in;
            }
        }
        pq.remove(prev);
        pq.remove(next);
        pq.offer(new Interval(prev == null ? p : prev.start, next == null ? p : next.end));
    }
}
_______________________________________________________________My Solution______________________________________________________________
// use list to record seat that has been occupied with ascending order
// travese list to get insert positon;
// O(n);
class ExamRoom {
    List<Integer> recorder = new ArrayList();
    int len;
    public ExamRoom(int N) {
        recorder = new ArrayList();
        len = N;
    }
    
    public int seat() {
        if(recorder.size() == 0){
            recorder.add(0);
            return 0;
        }
        
        int maxDis = 0, maxStart = -1, maxIdx = -1;
        if(recorder.get(0) != 0){
            maxDis = recorder.get(0);
            maxStart = 0;
            maxIdx = 0;
        }
        int dis = 0;
        for(int i = 1; i < recorder.size(); ++i){
            dis = (recorder.get(i) - recorder.get(i - 1)) / 2;
            if(dis > maxDis){
                maxDis = dis;
                maxStart = recorder.get(i - 1) + dis;
                maxIdx = i;
            }
        }
        if(recorder.get(recorder.size() - 1) != len - 1){
            dis = len - 1 - recorder.get(recorder.size() - 1);
            if(dis > maxDis){
                maxDis = dis;
                maxStart = len - 1;
                maxIdx = recorder.size();
            }
        }
        recorder.add(maxIdx, maxStart);
        return maxStart;
    }
    
    public void leave(int p) {
        recorder.remove((Integer)p);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
