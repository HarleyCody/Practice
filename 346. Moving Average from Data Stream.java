_________________________________________________________My Solution(Array)____________________________________________________
class MovingAverage {
    // deque can add element to tail and remove outdated data from head;
    // deque is faster than queue;
    public Deque<Integer> q;
    public int sum;
    public int sz ;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        q = new LinkedList<>() ;
        sum = 0 ;
        sz = size; 
    }
    // deque is faster than queue when it addLast rather than add removeFirst rather than remove() or poll();
    public double next(int val) {
        if(q.size() < sz){
            sum += val ; 
            q.addLast(val) ;
        }
        else if ( q.size() == sz){
            int front = q.removeFirst() ; 
            sum -= front; 
            sum += val; 
            q.addLast(val) ; 
        }
        return (sum * 1.0)/(q.size() * 1.0) ;
    }
}

_________________________________________________________My Solution(Queue)____________________________________________________
class MovingAverage {
    // record val for sake of deleting when the size is reached.
    Queue <Integer> recorder;
    int s, nums, ans;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        recorder = new LinkedList();
        this.s = size;
        nums = 0;
        ans = 0;
    }
    // keep recording and adding to ans when the size is not reached, otherwise, delete firstone in the queue and add new val;
    public double next(int val) {
        if(nums < s){
            ++nums;
        }else{
            ans -= recorder.poll();
        }
        ans +=val;
        recorder.offer(val);
        return (double) ans / nums;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
