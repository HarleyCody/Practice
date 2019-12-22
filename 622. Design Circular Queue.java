________________________________________________________________My Solution______________________________________________________________
// use LinkedList offer(), poll() when size > 0; peekFirst() when size > 0; peekLast() when size > 0; isEmpty(); return rec.size == K

class MyCircularQueue {
    int size;
    LinkedList<Integer> recorder = new LinkedList(); 
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        size = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(recorder.size() == size)return false;
        return recorder.offer(value);
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(recorder.size() == 0) return false;
        recorder.poll();
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(recorder.size() == 0) return -1;;
        return recorder.peekFirst();
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(recorder.size() == 0) return -1;
        return recorder.peekLast();
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return recorder.isEmpty();
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return recorder.size() == size;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
