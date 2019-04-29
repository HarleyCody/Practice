class RandomizedSet {
    
    List<Integer> random;
    HashMap<Integer,Integer> index; // record index, so it is easier and faster delete element by the index.
    int ind = 0;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        random = new ArrayList<>();
        index = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(index.containsKey(val))return false; // containsKey(Map) is way faster than contains(List);
        random.add(val);
        index.put(val,random.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        ind = index.getOrDefault(val,-1);
        if(ind == -1) return false;
        Collections.swap(random,ind,random.size()-1);// change the element to the last one, so the list will not need to move latter element forward.
        int update = random.get(ind); // get new val in swapped ind
        index.put(update,ind); // update HashMap for new val in ind
        random.remove(random.size()-1); // remove the last element which is target
        index.remove(val); // remove in the hashmap;
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int max = random.size();
        int min = 0;
        int ind = (int)(Math.random() * (max - min) + min);
        return random.get(ind);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
