______________________________________________________________Better Solution____________________________________________________________
class LogSystem {
    HashMap<String, Integer> map;
    List<String[]> list;
    public LogSystem() {
        map = new HashMap<>();
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);
        
        list = new ArrayList<>();
    }
    
    public void put(int id, String timestamp) {
        String[] sub = new String[2];
        sub[0] = Integer.toString(id);
        sub[1] = timestamp;
        list.add(sub);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        int idx = map.get(gra);
        List<Integer> res = new ArrayList<>();
        for(String[] ele : list){// Compare by string .compareTo(), start<= log && log <= end
            if(ele[1].substring(0, idx).compareTo(s.substring(0, idx)) >= 0 && ele[1].substring(0, idx).compareTo(e.substring(0, idx)) <= 0){
                res.add(Integer.parseInt(ele[0]));
            }
        }
        
        return res;
    }
}
______________________________________________________________My Solution________________________________________________________________
class LogSystem {
    class Log{
        int year;
        int mon;
        int day;
        int hour;
        int min;
        int sec;
        int id;
        public Log(){
            year = 0;
            mon = 0;
            day = 0;
            hour = 0;
            min = 0;
            sec = 0;
            id = 0;
        }
    }
    List<Log> recorder;
    
    public LogSystem() {
        recorder = new ArrayList<Log>();
    }
    
    public void put(int id, String t) {
        List<Integer> date = parse(t);
        Log newLog = new Log();
        int year = parse(t).get(0) - 2000;
        newLog.year = parse(t).get(0);
        newLog.mon = parse(t).get(1);
        newLog.day = parse(t).get(2);
        newLog.hour = parse(t).get(3);
        newLog.min = parse(t).get(4);
        newLog.sec = parse(t).get(5);
        newLog.id = id;
        recorder.add(newLog);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> start = parse(s);
        List<Integer> end = parse(e);
        List<Integer> ansList = new ArrayList();
        switch(gra){
            case "Year":
                for(Log l : recorder){
                    if(l.year < start.get(0) || l.year > end.get(0))continue;
                    
                    ansList.add(l.id);
                }
            break;
            case "Month":
                for(Log l : recorder){
                    if(l.year == start.get(0) && l.mon < start.get(1)
                       || (l.year == end.get(0) && l.mon > end.get(1))) continue;
                    
                    ansList.add(l.id);
                }
            break;
            case "Day":
                for(Log l : recorder){
                    if((l.year == start.get(0) && l.mon == start.get(1) && l.day < start.get(2))
                       || (l.year == end.get(0) && l.mon == end.get(1) && l.day > end.get(2))) continue;
                    
                    ansList.add(l.id);
                }
            break;
            case "Hour":
                for(Log l : recorder){
                    if((l.year == start.get(0) && l.mon == start.get(1) && l.day == start.get(2)&& l.hour < start.get(3))
                       || (l.year == end.get(0) && l.mon == end.get(1) && l.day == end.get(2) && l.hour > end.get(3))){
                        continue;
                    }
                    ansList.add(l.id);
                }
            break;
            case "Minute":
                for(Log l : recorder){
                    if((l.year == start.get(0) && l.mon == start.get(1) && l.day == start.get(2)
                        && l.hour == start.get(3) && l.min < start.get(4))
                       || (l.year == end.get(0) && l.mon == end.get(1) && l.day == end.get(2)
                           && l.hour == end.get(3) && l.min > end.get(4))){
                        continue;
                    }
                    ansList.add(l.id);
                }
            break;
            case "Second":
                for(Log l : recorder){
                    if((l.year == start.get(0) && l.mon == start.get(1) && l.day == start.get(2)
                        && l.hour == start.get(3) && l.min == start.get(4) && l.sec < start.get(5))
                       || (l.year == end.get(0) && l.mon == end.get(1) && l.day == end.get(2)
                           && l.hour == end.get(3) && l.min == end.get(4) && l.sec > end.get(5))){
                        continue;
                    }
                    ansList.add(l.id);
                }
            break;
            default:
            break;
        }
        return ansList;
    }
    
    private List<Integer> parse(String str){
        List<Integer> ans = new ArrayList();
        String [] split = str.split(":");
        for(String i : split){
            int num = 0;
            for(Character c : i.toCharArray()){
                num = num * 10 + (c - '0');
            }
            ans.add(num);
        }
        return ans;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
