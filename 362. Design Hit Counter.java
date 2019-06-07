class HitCounter {
    
    //Deque record hits in last 5 minutes;
    Deque<Integer> record;
    /** Initialize your data structure here. */
    public HitCounter() {
        record = new LinkedList();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        //renew reocrd
        checkRecord(timestamp);
        record.addLast(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        //renew record
        checkRecord(timestamp);
        return record.size();
    }
    
    public void checkRecord(int timestamp){
        if( timestamp > 300){// start take expiration into account
            int start = timestamp - 300 + 1;// least start time
            
            //record can be peeked && First record is expired
            while(record.size() > 0 && record.peekFirst() < start){
                record.poll();
            }
        }
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
