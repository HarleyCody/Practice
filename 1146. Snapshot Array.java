__________________________________________________________________________My Solution 2________________________________________________________________________________________
class SnapshotArray {
    // improve use array instead of hashmap
    // critical point is how to clone original arr to recorder, clone will MLE
    // only record 0 to maxIdx data to recorder;
    int id, len, maxIdx = 0;
    List<int[]> recorder;
    int[] cur;
    public SnapshotArray(int length) {
        id = 0;
        len = length;
        cur = new int[length];
        recorder = new ArrayList<int[]>();
    }
    
    public void set(int index, int val) {
        if(index < len){
            maxIdx = Math.max(maxIdx, index);
            cur[index] = val;
        }
    }
    
    public int snap() {
        int[] record = new int[maxIdx + 1];
        for(int i = 0; i <= maxIdx; ++i){
            record[i] = cur[i];
        }
        recorder.add(record);
        return id++;
    }
    
    public int get(int index, int snap_id) {
        int[] record = recorder.get(snap_id);
        if(snap_id >= id || record.length <= index){
            return 0;
        }
        return recorder.get(snap_id)[index];
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */

__________________________________________________________________________My Solution 1________________________________________________________________________________________
class SnapshotArray {
    //use hashmap as records to store snapshot
    int id, len;
    List<HashMap<Integer, Integer>> recorder;
    HashMap<Integer, Integer> cur;
    public SnapshotArray(int length) {
        id = 0;
        len = length;
        recorder = new ArrayList();
        cur = new HashMap();
    }
    
    public void set(int index, int val) {
        if(index <= len){
            cur.put(index, val);
        }
    }
    
    public int snap() {
        recorder.add((HashMap<Integer, Integer>)cur.clone());
        return id++;
    }
    
    public int get(int index, int snap_id) {
        if(snap_id > id){
            return -1;
        }
        return recorder.get(snap_id).getOrDefault(index, 0);
    }
}
