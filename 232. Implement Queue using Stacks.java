class MyQueue {
    Stack<Integer> recorder;
    Stack<Integer> reversedRecorder;
    /** Initialize your data structure here. */
    public MyQueue() {
        recorder = new Stack();
        reversedRecorder = new Stack();
    }
    
    /** Push element x to the back of queue. */
    // push recorder to reversedRecorder();
    // push new element to rRecorder;
    // push back reverseRecorder to recorder(); so out sequence is same as queue;
    public void push(int x) {
        while(!recorder.isEmpty()){
            reversedRecorder.push(recorder.pop());
        }
        reversedRecorder.push(x);
        while(!reversedRecorder.isEmpty()){
            recorder.push(reversedRecorder.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return recorder.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return recorder.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return recorder.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
