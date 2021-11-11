//My Solution: PQ to recorder min and max, hashmap to recorder real value, cur record cur status
class StockPrice {
    HashMap<Integer, Integer> recorder;
    PriorityQueue<int[]> maxQ;
    PriorityQueue<int[]> minQ;
    int[] cur;
    public StockPrice() {
        recorder = new HashMap<Integer, Integer>();
        maxQ = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
        minQ = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        cur = new int[2];
    }
    
    public void update(int timestamp, int price) {
        recorder.put(timestamp, price);
        maxQ.offer(new int[]{timestamp, price});
        minQ.offer(new int[]{timestamp, price});
        if(cur[0] <= timestamp){
            cur[0] = timestamp;
            cur[1] = price;
        }
    }
    
    public int current() {
        return cur[1];
    }
    
    public int maximum() {
        int[] max = maxQ.peek();
        while(recorder.get(max[0]) != maxQ.peek()[1]){
            maxQ.poll();
            max = maxQ.peek();
        }
        return max[1];
    }
    
    public int minimum() {
        int[] min = minQ.peek();
        while(recorder.get(min[0]) != minQ.peek()[1]){
            minQ.poll();
            min = minQ.peek();
        }
        return min[1];
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
