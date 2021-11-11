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

//My Solution: Segment Tree record min max within a range
class StockPrice {
    class Node{
        int left;
        int right;
        int min;
        int max;
        Node lKid;
        Node rKid;
        public Node(int h, int t, int v){
            left = h;
            right = t;
            min = v;
            max = v;
        }
        
        public void update(int time, int price){
            int mid = (left + right) >> 1;
            if(left != right){
                if(time <= mid){
                    if(lKid == null){
                        lKid = new Node(left, mid, price);
                    }
                    lKid.update(time, price);
                }else{
                    if(rKid == null){
                        rKid = new Node(mid + 1, right, price);
                    }
                    rKid.update(time, price);
                }
            }
            min = price;
            max = price;
            if(lKid != null){
                min = Math.min(min, lKid.min);
                max = Math.max(max, lKid.max);
            }
            if(rKid != null){
                min = Math.min(min, rKid.min);
                max = Math.max(max, rKid.max);
            }
        }
    }
    Node root;
    int curTime;
    int cur;
    public StockPrice() {
        root = new Node(0, (int)1e9, 0);
    }
    
    public void update(int timestamp, int price) {
        root.update(timestamp, price);
        if(curTime <= timestamp){
            curTime = timestamp;
            cur = price;
        }
    }
    
    public int current() {
        return cur;
    }
    
    public int maximum() {
        return root.max;
    }
    
    public int minimum() {
        return root.min;
    }
}

