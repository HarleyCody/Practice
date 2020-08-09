______________________________________________________________________________My Solution_________________________________________________________________
// check freq before poll and return, only return if its unique
class FirstUnique {
    HashMap<Integer, Integer> recorder = new HashMap<Integer, Integer>();
    Queue<Integer> data = new LinkedList();
    public FirstUnique(int[] nums) {
        for(int num : nums){
            add(num);
        }
    }
    
    public int showFirstUnique() {
        while(!data.isEmpty()){
            int cur = data.peek();
            if(recorder.get(cur) == 1){
                return cur;
            }
            data.poll();
        }
        return -1;
    }
    
    public void add(int value) {
        int f = recorder.getOrDefault(value, 0);
        recorder.put(value, f + 1);
        data.offer(value);
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
