__________________________________________________________________________SegmentTree Solution___________________________________________________________________________
// slower than treeMap
class RangeModule {

    class SegNode {
        int left, right, mid;
        SegNode lc, rc;
        boolean modified;
        boolean covered;
        boolean empty;
        public SegNode(int left, int right){
            this.left=left;
            this.right=right;
            this.mid=left+(right-left)/2;
            this.empty=true;
        }
        void addRange(int left, int right){
            if (left<=this.left&&right>=this.right){
                if (!covered) {
                    covered = true;
                    modified=true;
                    empty=false;
                }
            } else {
                pushdown();
                if (left<=mid) this.lc.addRange(left, right);
                if (right>mid)this.rc.addRange(left, right);
                maintain();
            }
        }
        void maintain(){
            this.covered=this.lc.covered&&this.rc.covered;
            this.empty=this.lc.empty&&this.rc.empty;
        }
        void removeRange(int left, int right){
            if (left<=this.left&&right>=this.right){
                if (!empty){
                    empty=true;
                    covered=false;
                    modified=true;
                }
            } else {
                pushdown();
                if (left<=mid)this.lc.removeRange(left, right);
                if (right>mid)this.rc.removeRange(left, right);
                maintain();

            }
        }
        boolean query(int left, int right){
            if (this.empty)return false;
            if (this.covered)return true;
            if (left<=this.left&&right>=this.right) {
                return this.covered;
            }
            boolean ans=true;
            if (left<=mid)ans&=this.lc.query(left, right);
            if (right>mid)ans&=this.rc.query(left, right);
            return ans;
        }
        void pushdown(){
            if (this.lc==null){
                this.lc=new SegNode(left, mid);
                this.lc.covered=this.covered;
                this.lc.empty=this.empty;
                this.rc=new SegNode(mid+1,right);
                this.rc.covered=this.covered;
                this.rc.empty=this.empty;
                this.modified=false;
            } else if (modified){
                this.lc.covered=this.covered;
                this.lc.empty=this.empty;
                this.lc.modified=true;
                this.rc.covered=this.covered;
                this.rc.empty=this.empty;
                this.rc.modified=true;
                this.modified=false;
            }
        }
    }
    SegNode root;
    public RangeModule() {
        root =new SegNode(1, 1_000_000_000);
    }

    public void addRange(int left, int right) {
        root.addRange(left, right-1);

    }

    public boolean queryRange(int left, int right) {
        return root.query(left, right-1);

    }

    public void removeRange(int left, int right) {
        root.removeRange(left, right-1);

    }
}
___________________________________________________________________________My Solution___________________________________________________________________________
class RangeModule {
    // TreeMap update <Start, End> from floor(start) to Ceiling(right);
    // query in range
    TreeMap<Integer, Integer> recorder;
    public RangeModule() {
       recorder = new TreeMap();
    }
    
    public void addRange(int left, int right) {
        // go to left most combine multiple intervals that is in range left to right as one interval
        Integer lKey = recorder.floorKey(left);
        if(lKey != null && recorder.get(lKey) >= left){
            left = lKey;
        }
        Integer rKey = recorder.ceilingKey(left);
        int r = right;
        // expand new interval one by one
        while(rKey != null && rKey <= right){
            r = Math.max(right, recorder.get(rKey));
            recorder.remove(rKey);
            rKey = recorder.higherKey(rKey);
        }
        
        int nl = left;
        int nr = r;
        
        recorder.put(nl, nr);
    }
        
    public boolean queryRange(int left, int right) {
        Integer key = recorder.floorKey(left);
        if(key == null){
            return false;
        }
        int val = recorder.get(key);
        
        return val >= right;
    }
    
    public void removeRange(int left, int right) {
        Integer lKey = recorder.floorKey(left);
        if(lKey != null){
            if(right < recorder.get(lKey)){
                recorder.put(right, recorder.get(lKey));
            }
            // left has on verlap with deleting range
            // narrow down left
            if(recorder.get(lKey) >= left){
                recorder.remove(lKey);
                recorder.put(lKey, left);
            }
        }
        
        Integer rKey = recorder.ceilingKey(left);
        while(rKey != null && recorder.get(rKey) <= right){
            recorder.remove(rKey);
            rKey = recorder.higherKey(rKey);
        }
        // right of record is still valid
        if(rKey != null && rKey < right){
            recorder.put(right, recorder.get(rKey));
            recorder.remove(rKey);
        }
    }
}
