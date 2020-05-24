____________________________________________________________________Best Solution_______________________________________________________
class SummaryRanges {
    // insert in ascending order
    private ArrayList<int[]> intvs;
    public SummaryRanges() {
        intvs = new ArrayList();
    }

    public void addNum(int val) {
        ArrayList<int[]> newIntvs = new ArrayList();
        boolean inserted = false;
        int[] mergedIntv = { val, val };
        for (int i = 0; i < intvs.size(); ++i) {
            if (inserted) {
                newIntvs.add(intvs.get(i));
                continue;
            }
            // add new record when val < start of next interval
            // set true to avoid add new record multiple times
            if (val < intvs.get(i)[0] - 1) {
                newIntvs.add(mergedIntv);
                newIntvs.add(intvs.get(i));
                inserted = true;
            } else if (val == intvs.get(i)[0] - 1){
                mergedIntv[1] = intvs.get(i)[1];
            } else if (val < intvs.get(i)[1] + 1) {
                newIntvs.add(intvs.get(i));
                inserted = true;
            } else if (val == intvs.get(i)[1] + 1){
                mergedIntv[0] = intvs.get(i)[0];
            } else{
                newIntvs.add(intvs.get(i));
            }
        }

        if (!inserted)
            newIntvs.add(mergedIntv);
        intvs = newIntvs;
    }

    public int[][] getIntervals() {
        return intvs.toArray(new int[intvs.size()][]);
    }
}
_______________________________________________________________________My Solution_______________________________________________________
// only add new value, and store prevous answer to return if addNum is not adding a new value
class SummaryRanges {
    /** Initialize your data structure here. */
    HashSet<Integer> added = new HashSet();
    Map<Integer, Integer> headCache;
    Map<Integer, Integer> tailCache;
    int[][] ans = new int[0][0];
    boolean changed = false;
    public SummaryRanges() {
        headCache = new TreeMap();
        tailCache = new TreeMap();
    }
    
    public void addNum(int val) {
        if(!added.add(val)){
            changed = false;
            return;
        }
        changed = true;
        int[] newData = {val, val};
        
        if(headCache.containsKey(val + 1)){
            newData[1] = headCache.get(val + 1);
            headCache.remove(val + 1);
        }
        
        if(tailCache.containsKey(val - 1)){
            newData[0] = tailCache.get(val - 1);
            tailCache.remove(val - 1);
        }
        
        headCache.put(newData[0], newData[1]);
        tailCache.put(newData[1], newData[0]);
        //update ans as they may call multiple addNum and one time getIntervals
        getIntervals();
    }
    
    public int[][] getIntervals() {
        if(!changed){
            return ans;
        }
        int size = headCache.size();
        ans = new int[size][2];
        int idx = 0;
        for(Map.Entry<Integer,Integer> e : headCache.entrySet()){
            ans[idx][0] = e.getKey();
            ans[idx++][1] = e.getValue();
        }
        return ans;
    }
}
