_________________________________________________________________________Best Solution(Array)______________________________________________________________________
class MovingAverage {
    // one pointers recorder tail(should be updated) to count sum;
    // tail records value of previous kth element, subtract first and then update array
    private int window;
    private int[] array;
    private double sum = 0.0;
    private int tail = 0;
    private int count = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = size;
        array = new int[size];
    }
    
    public double next(int val) {
        if(count < window)
            count++;
        sum -= array[tail];
        sum += val;
        array[tail] = val;
        tail = (tail + 1) % window;
        return sum / count;
    }
}
_________________________________________________________________________My Solution(List)______________________________________________________________________
class MovingAverage {
    // maintain k value within sliding windows
    // update sliding windows and sum.
    /** Initialize your data structure here. */
    LinkedList<Integer> list = new LinkedList();
    int size;
    double sum = 0;
    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        sum += val;
        list.offer(val);
        double ans = 0.0;
        if(list.size() > size){
            sum -= list.poll();
            ans = sum / size;
        }else{
            ans = sum / list.size();
        }
        
        return ans;
    }
}
