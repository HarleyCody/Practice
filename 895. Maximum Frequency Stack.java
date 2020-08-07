____________________________________________________________________________________My Solution_______________________________________________________________
class FreqStack {
    // use stack to record status in every freq level
    // clean level when stack is empty
    // only pop the last one;
    // use linkedlist instea of stack, linkedlist will be faster and safer as stack implement linkedlist
    List<LinkedList<Integer>> recorder = new ArrayList();
    HashMap<Integer, Integer> freq = new HashMap();
    public FreqStack() {
        
    }
    
    public void push(int x) {
        int f = freq.getOrDefault(x, 0);
        if(recorder.size() == f){
            recorder.add(new LinkedList());
        }
        freq.put(x, f + 1);
        recorder.get(f).offer(x);
    }
    
    public int pop() {
        int size = recorder.size();
        LinkedList<Integer> maxStack = recorder.get(size - 1);
        
        int ans = maxStack.pollLast();
        if(maxStack.isEmpty()){
            recorder.remove(size - 1);
        }
        freq.put(ans, size - 1);
        return ans;
    }
}
