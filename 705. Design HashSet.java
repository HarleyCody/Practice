________________________________________________________________________My Solution________________________________________________________________________
class MyHashSet {
    /** Initialize your data structure here. */
    List<Integer>[] set;
    int len = 100;
    public MyHashSet() {
        set = new LinkedList[len];
        for(int i = 0; i < len; ++i){
            set[i] = new LinkedList<Integer>();
        }
    }
    
    public void add(int key) {
        int bucket = key % len;
        for(int val : set[bucket]){
            if(val == key){
                return;
            }
        }
        set[bucket].add(key);
    }
    
    public void remove(int key) {
        int bucket = key % len;
        set[bucket].remove((Integer)key);
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucket = key % len;
        for(int val : set[bucket]){
            if(val == key){
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
