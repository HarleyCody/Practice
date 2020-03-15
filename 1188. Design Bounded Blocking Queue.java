___________________________________________________________________Best Solution_________________________________________________________
import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.locks.Condition; 

class BoundedBlockingQueue {
    // multi threds
    // two condition for different cases
    // operate with queue, lock resource, unlock in finally step
    // finally with execute after try block with exception(invoke by await)
    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private Queue<Integer> q;
    private final int capacity;
    
    
    public BoundedBlockingQueue(int capacity) {
        q = new LinkedList<>();
        this.capacity = capacity;
        
    }
    
    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            if (q.size() == this.capacity)
                full.await();
            q.offer(element);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            if (q.size() == 0)
                empty.await();
            int res = q.poll();
            full.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }
    
    public int size() {
        lock.lock();
        try {
            return q.size();
        } finally {
            lock.unlock();
        }
    }
}
