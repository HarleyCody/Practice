________________________________________________________Best Solution__________________________________________________________
class Solution {
    //todo sort by length then merge
    //todo bottom up binary merge, no implicit call stack
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int sl = schedule.size();
        List<Interval> totalWorking = merge(schedule,0,sl-1);   
        int lenm1 = totalWorking.size()-1;
        List<Interval> res = new ArrayList<>(lenm1);
        for(int i=0;i<lenm1;){
           res.add(new Interval(totalWorking.get(i).end,totalWorking.get(++i).start)); 
        }
        return res;
         
    }
    // binary merge
    private List<Interval> merge(List<List<Interval>> s,int l, int r){
       if(l==r) return s.get(l);
        int mid = l + (r - l) / 2;
        List<Interval> li = merge(s,l,mid);
        List<Interval> ri = merge(s,mid+1,r);
        return merge2(li,ri);
    }
    // maintain pre interval to merge two intervals;
    private List<Interval> merge2(List<Interval> l,List<Interval> r){
        List<Interval> res = new ArrayList<>();
        int pl = 0, pr = 0, llen = l.size(), rlen = r.size(); 
        Interval ls = l.get(0),rs = r.get(0); 
        Interval pre;
        if(ls.start <= rs.start){
            pre = ls; 
            ls = ++pl == llen ? null:l.get(pl); 
        }else{
            pre = rs; 
            rs = ++pr == rlen ? null:r.get(pr);
        }
        res.add(pre);
        while(pl < llen && pr < rlen){
           if(ls.start <= rs.start){
              if(ls.start <= pre.end){
                  pre.end = Math.max(pre.end,ls.end);
              }else{
                  // extend range
                  pre = ls; 
                  res.add(pre);
              } 
              //todo priority after : remove redundant pointer boundary check 
              if(++pl >= llen) break; 
              ls = l.get(pl); 
           }else{
              if(rs.start <= pre.end){
                  pre.end = Math.max(pre.end,rs.end);
              }else{
                  // extend range 
                  pre = rs;
                  res.add(pre);
              } 
               
              if(++pr >= rlen) break;
              rs = r.get(pr); 
           } 
        }
        // extend rest
        while(pl < llen){
              if(ls.start <= pre.end){
                  pre.end = Math.max(pre.end,ls.end);
              }else{
                  pre = ls;
                  res.add(pre);
              } 
              if(++pl >= llen) break;
              ls = l.get(pl); 
        }
        while(pr < rlen){
              if(rs.start <= pre.end){
                  pre.end=Math.max(pre.end,rs.end);
              }else{
                  pre = rs;
                  res.add(pre);
              } 
              if(++pr >= rlen) break;
              rs = r.get(pr); 
        }
        return res;
    }
}
___________________________________________________My Solution_______________________________________________________________
/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/
// find max range of start i;
// sort by i, then find interval if there is interval, put them cur next back otherwise combine them. 
class Solution {
    HashMap<Integer, Integer> maxRange = new HashMap();
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        LinkedList<Interval> recorder = new LinkedList();
        for(List<Interval> list: schedule){
            for(Interval i: list){
                int max = maxRange.getOrDefault(i.start, -1);
                maxRange.put(i.start, Math.max(max, i.end));
            }
        }
        for(Integer key : maxRange.keySet()){
            recorder.add(new Interval(key, maxRange.get(key)));
        }
        List<Interval> ans = new ArrayList();
        Collections.sort(recorder, (x,y) ->(x.start - y.start));
        for(int i = 0; i < recorder.size(); ++i){
            Interval cur = recorder.get(i);
            if(i == recorder.size() - 1) break;
            Interval next = recorder.get(i + 1);
            if(cur.end < next.start){
                recorder.set(i + 1, next);
                recorder.set(i, cur);
                ans.add(new Interval(cur.end, next.start));
            }else{
                cur.end = Math.max(cur.end, next.end);
                recorder.remove(i + 1);
                recorder.set(i, cur);
                --i;
            }
        }
        return ans; 
    }
}
