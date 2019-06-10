class MaxStack {
    PriorityQueue<Integer> maxRe;
    List<Integer> orig;
    /** initialize your data structure here. */
    public MaxStack() {
        
        //Rcord sequence of Max Value; avoid sort everytime;
        maxRe = new PriorityQueue<>((x, y) -> y - x);
        //Original sequence of number;
        orig = new ArrayList();
    }
    
    public void push(int x) {
        orig.add(x);
        maxRe.add(x);
    }
    
    public int pop() {
        
        int out = orig.get(orig.size() - 1);
        //remove last one and return it;
        orig.remove(orig.size() - 1);
        //remove from recorder.
        maxRe.remove(out);
        return out;
    }
    
    public int top() {
        // last element of orig.
        return orig.get(orig.size() - 1);
    }
    
    public int peekMax() {
        // peek recorder;
        return maxRe.peek();
    }
    
    public int popMax() {
        int i = orig.size()-1;
        int max = maxRe.poll();
        
        // find index of max from tail to head, remove from tail;
        while(0 <= i && orig.get(i) != max) --i;
        
        // list remove throught index. if it is value should use (Integer) value to remove;
        orig.remove(i);
        return max;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
