_____________________________________________________________________________Best Solution________________________________________________________________________________
class MyCalendar {
    //TreeMap sort and fast in insertion and find
    // floor will get closest child, ceiling will get parent
    // child should end before cur and parent should start later than end
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
class MyCalendar {
    // naive solution check every slot one by one
    HashMap<Integer, Integer> recorder;
    public MyCalendar() {
        recorder = new HashMap();
    }
    
    public boolean book(int start, int end) {
        if(recorder.size() == 0){
            recorder.put(start, end);
            return true;
        }
        
        for(Map.Entry<Integer,Integer> e : recorder.entrySet()){
            int from = e.getKey();
            int to = e.getValue();
            if(!(end <= from || start >= to)){
                return false;
            } 
        }
        recorder.put(start, end);
        return true;
    }
}
