_____________________________________________________________________My Solution_________________________________________________________
class RandomizedCollection {
    /** Initialize your data structure here. */
    /** Rules: self-incresing offset for putting a value
      *         removing idx of values in HashMap
      *         getRandom by generate idx in range of (0 - offset); if idx is contained, return otherwise generate a new idx to get value
      */
    // idx to value
    HashMap<Integer, Integer> vals;
    // val to indexes
    HashMap<Integer, LinkedList<Integer>> indexes;
    Random rand;
    int offset = 0;
    public RandomizedCollection() {
        vals = new HashMap();
        indexes = new HashMap();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        vals.put(offset, val);
        if(!indexes.containsKey(val)){
            indexes.put(val, new LinkedList());
        }
        indexes.get(val).offer(offset++);
        return indexes.get(val).size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!indexes.containsKey(val)){
            return false;
        }
        int idx = indexes.get(val).removeLast();
        vals.remove(idx);
        if(indexes.get(val).size() == 0){
            indexes.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int idx = rand.nextInt(offset);
        while(!vals.containsKey(idx)){
            idx = rand.nextInt(offset);
        }
        return vals.get(idx);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
