___________________________________________________________________________________Best Solution____________________________________________________________________________________
// add until overlaps
// add left and add right
// add rest non-overlaps intervals
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        final int length = intervals.length;
        for (; i<length; i++) {
            if (intervals[i][1]<=toBeRemoved[0]) 
                addToRes(res, intervals[i][0], intervals[i][1]);
            else break;
        }
        if (i==length) return res;
        if (intervals[i][0]<toBeRemoved[0]) 
            addToRes(res, intervals[i][0], toBeRemoved[0]);
        while (i<length && intervals[i][1]<=toBeRemoved[1]) i+=1;
        if (i==length) return res;
        if (intervals[i][0]<toBeRemoved[1]) 
            addToRes(res, toBeRemoved[1], intervals[i++][1]);
        while (i<length) 
            addToRes(res, intervals[i][0], intervals[i++][1]);
        return res;
    }
    
    protected void addToRes(List<List<Integer>> res, int start, int end) {
        List<Integer> temp = new ArrayList<>();
        temp.add(start);
        temp.add(end);
        res.add(temp);
    }
}
____________________________________________________________________________________My Solution____________________________________________________________________________________
// new ArrayList and add will be faster
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        
        List<List<Integer>> ans = new ArrayList();
        List<Integer> med;
        for(int[] inter : intervals){
            if(toBeRemoved[0] > inter[0] && toBeRemoved[1] < inter[1]){
                med = new ArrayList();
                med.add(inter[0]);
                med.add(toBeRemoved[0]);
                ans.add(med);
                med = new ArrayList();
                med.add(toBeRemoved[1]);
                med.add(inter[1]);
                ans.add(med);
            }else{
                if(inter[0] < toBeRemoved[0]){
                    med = new ArrayList();
                    med.add(inter[0]);
                    med.add(Math.min(toBeRemoved[0], inter[1]));
                    ans.add(med);
                }else if(inter[1] > toBeRemoved[1]){
                    med = new ArrayList();
                    med.add(Math.max(toBeRemoved[1], inter[0]));
                    med.add(inter[1]);
                    ans.add(med);
                }
            }
        }
        return ans;
    }
}
______________________________________________________________________________________My First Solution_________________________________________________________________________
// use Arrays.asList instead of new ArrayList and add
// Interval contains toBeRemoved add left and right
// Interval overlaps toBeRemoved delete overlap
// Interval contains toBeRemoved add left and right
// Interval overlaps toBeRemoved delete overlap
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        
        List<List<Integer>> ans = new ArrayList();
        for(int[] inter : intervals){
            if(toBeRemoved[0] > inter[0] && toBeRemoved[1] < inter[1]){
                ans.add(Arrays.asList(inter[0], toBeRemoved[0]));
                ans.add(Arrays.asList(toBeRemoved[1], inter[1]));
            }else{
                if(inter[0] < toBeRemoved[0]){
                    ans.add(Arrays.asList(inter[0], Math.min(toBeRemoved[0], inter[1])));
                }else if(inter[1] > toBeRemoved[1]){
                    ans.add(Arrays.asList(Math.max(toBeRemoved[1], inter[0]), inter[1]));
                }
                            
            }
        }
        return ans;
    }
}
